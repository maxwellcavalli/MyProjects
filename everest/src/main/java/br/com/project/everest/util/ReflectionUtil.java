package br.com.project.everest.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.project.everest.annotation.JsonColumn;
import br.com.project.everest.domain.base.CrudDomain;
import br.com.project.everest.type.Type;

public class ReflectionUtil {

	public static Type getType(Field field) {
		if (field.getType().equals(String.class)) {
			return Type.STRING;
		} else if (field.getType().equals(Integer.class) || field.getType().equals(Integer.TYPE)) {
			return Type.INTEGER;
		} else if (field.getType().equals(Long.class) || field.getType().equals(Long.TYPE)) {
			return Type.LONG;
		} else if (field.getType().equals(BigDecimal.class)) {
			return Type.BIGDECIMAL;
		} else if (field.getType().equals(Date.class)) {
			return Type.DATE;
		} else {
			return Type.UNKNOWN;
		}
	}

	public static String getFieldValue(Object object, Type type)
			throws IllegalArgumentException, IllegalAccessException {
		String sValue = "";
		if (type.equals(Type.STRING)) {
			sValue = "\"";
			if (object != null) {
				sValue = sValue + (String) object;
			}
			sValue = sValue + "\"";
		} else if (type.equals(Type.INTEGER)) {
			if (object != null) {
				sValue = String.valueOf((Integer) object);
			}
		} else if (type.equals(Type.LONG)) {
			if (object != null) {
				sValue = String.valueOf((Long) object);
			}
		} else if (type.equals(Type.BIGDECIMAL)) {
			if (object != null) {
				DecimalFormat df = new DecimalFormat("#0.00");
				BigDecimal bd = (BigDecimal) object;

				sValue = "\"";
				sValue = df.format(bd.doubleValue());
				sValue = sValue + "\"";
			}
		} else if (type.equals(Type.DATE)) {
			if (object != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date d = (Date) object;
				sValue = "\"";
				sValue = sdf.format(d);
				sValue = sValue + "\"";
			}
		}

		return sValue;
	}

	public static String objectToJson(Object parent) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder builder = new StringBuilder();
		builder.append("{");

		if (parent != null && Hibernate.isInitialized(parent)) {
			if (parent.getClass().isAnnotationPresent(JsonColumn.class)) {
				if (parent instanceof CrudDomain) {
					CrudDomain baseDomain = (CrudDomain) parent;
					JsonColumn jsonIdColumn = parent.getClass().getAnnotation(JsonColumn.class);

					/*id*/
					builder.append("\"");
					builder.append(jsonIdColumn.id());
					builder.append("\"");
					builder.append(":");
					builder.append(baseDomain.getId());
					builder.append(",");
					
					/*name*/
					builder.append("\"");
					builder.append(jsonIdColumn.name());
					builder.append("\"");
					builder.append(":");
					builder.append("\"");
					builder.append(baseDomain.getName());
					builder.append("\"");
					builder.append(",");
					
				}
			}

			Field[] fields = parent.getClass().getDeclaredFields();

			for (int x = 0; x < fields.length; x++) {
				Field field = fields[x];
				field.setAccessible(true);

				String fieldName = field.getName();

				if (fieldName.equals("serialVersionUID")) {
					continue;
				}

				if (field.isAnnotationPresent(JsonProperty.class)) {
					JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
					fieldName = jsonProperty.value();
				}

				builder.append("\"");
				builder.append(fieldName);
				builder.append("\"");
				builder.append(":");

				Type type = ReflectionUtil.getType(field);
				Object object = field.get(parent);

				if (!type.equals(Type.UNKNOWN)) {
					String sValue = ReflectionUtil.getFieldValue(object, type);
					builder.append(sValue);
				} else {
					builder.append(objectToJson(object));
				}

				if (x + 1 < fields.length) {
					builder.append(",");
				}
			}
		}

		builder.append("}");

		return builder.toString();
	}
}
