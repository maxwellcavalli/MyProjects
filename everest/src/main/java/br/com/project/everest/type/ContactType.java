package br.com.project.everest.type;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ContactType {

	TELEPHONE, CELLPHONE, FAX, EMAIL;

	private static Map<String, ContactType> namesMap = new HashMap<String, ContactType>(4);

	static {
		namesMap.put("TELEPHONE", TELEPHONE);
		namesMap.put("CELLPHONE", CELLPHONE);
		namesMap.put("FAX", FAX);
		namesMap.put("EMAIL", EMAIL);
	}

	@JsonCreator
	public static ContactType fromString(String value) {
		return namesMap.get(value);
	}

}
