package br.com.project.everest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.project.everest.domain.base.CrudDomain;

@Entity
@Table(name = "districts")
public class District implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "district_id")
	@JsonProperty("districts_code")
	private Long id;

	@Column(name = "name", length = 100, unique = false, nullable = true)
	@JsonProperty("districts_name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", referencedColumnName = "city_id", foreignKey = @ForeignKey(name = "Fk_district_city"))
	@JsonProperty("districts_city")
	private City city;

	public District() {
		id = 0l;
	}

	@Transient
	public Long getCode() {
		return this.id;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", name=" + name + ", city=" + city + "]";
	}

}