package br.com.project.everest.domain.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.project.everest.util.ReflectionUtil;

@MappedSuperclass
public class CrudDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@JsonValue
	@Transient
	public String getJsonValue() {
		try {
			return ReflectionUtil.objectToJson(this);
		} catch (Exception e) {
			return "";
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
