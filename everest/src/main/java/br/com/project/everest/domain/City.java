package br.com.project.everest.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.project.everest.annotation.JsonColumn;
import br.com.project.everest.domain.base.CrudDomain;

@Entity
@Table(name = "cities")

@AttributeOverrides({ 
	@AttributeOverride(name = "id", column = @Column(name = "city_id")),
	@AttributeOverride(name = "name", column = @Column(name = "name")) })
@JsonColumn(id="cities_id", name="cities_name")
public class City extends CrudDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", referencedColumnName = "state_id", foreignKey = @ForeignKey(name = "Fk_city_state"))
	@JsonProperty("cities_state")
	private State state;
	
	@Override
	@JsonProperty("cities_id")
	public void setId(Long id) {
		super.setId(id);
	}
	
	@Override
	@JsonProperty("cities_name")
	public void setName(String name) {
		super.setName(name);
	}
	

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "City [state=" + state + ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}
	

}