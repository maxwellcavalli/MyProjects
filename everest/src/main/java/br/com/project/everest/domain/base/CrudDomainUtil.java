package br.com.project.everest.domain.base;

import java.io.Serializable;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.project.everest.util.ReflectionUtil;

public class CrudDomainUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonValue
	@Transient
	public String getJsonValue() {
		try {
			String value = ReflectionUtil.objectToJson(this); 
			//System.out.println(value);
			
			return value;
		} catch (Exception e) {
			return "";
		}
	}
}
