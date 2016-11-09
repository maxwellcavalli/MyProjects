package br.com.project.everest.domain;

import java.io.Serializable;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.project.everest.util.ReflectionUtil;

public class BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonValue
	@Transient
	public String getJsonValue() {
		try {
			return ReflectionUtil.objectToJson(this);
		} catch (Exception e) {
			return "";
		}
	}

}
