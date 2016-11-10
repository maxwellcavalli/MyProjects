package br.com.project.everest.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.project.everest.annotation.JsonColumn;
import br.com.project.everest.domain.base.CrudDomain;

@Entity
@Table(name = "states")
@AttributeOverrides({ 
	@AttributeOverride(name = "id", column = @Column(name = "state_id")),
	@AttributeOverride(name = "name", column = @Column(name = "name")) })

@JsonColumn(id="states_id", name="states_name")
public class State extends CrudDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "abreviation", length = 3, unique = true, nullable = false)
	@JsonProperty("states_abreviation")
	private String abreviation;

	@Override
	@JsonProperty("states_id")
	public void setId(Long id) {
		super.setId(id);
	}
	
	@Override
	@JsonProperty("states_name")
	public void setName(String name) {
		super.setName(name);
	}

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}


	@Override
	public String toString() {
		return "State [id=" + getId() + ", abreviation=" + abreviation + ", name=" + getName() + "]";
	}
}