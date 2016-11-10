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
@Table(name = "districts")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "district_id")),
		@AttributeOverride(name = "name", column = @Column(name = "name")) })
@JsonColumn(id = "districts_id", name = "districts_name")
public class District extends CrudDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", referencedColumnName = "city_id", foreignKey = @ForeignKey(name = "Fk_district_city"))
	@JsonProperty("districts_city")
	private City city;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "District [city=" + city + ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}

}