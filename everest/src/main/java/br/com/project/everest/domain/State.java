package br.com.project.everest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "states")
public class State extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "state_id")
	@JsonProperty("states_code")
	private Long id;

	@NotNull
	@Column(name = "abreviation", length = 3, unique = true, nullable = false)
	@JsonProperty("states_abreviation")
	private String abreviation;

	@NotNull
	@Column(name = "name", length = 100, unique = false, nullable = true)
	@JsonProperty("states_name")
	private String name;

	public State() {
		id = 0l;
	}
	
	public void setCode(Long code){
		this.id = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", abreviation=" + abreviation + ", name=" + name + "]";
	}

}