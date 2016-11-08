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

@Entity
@Table(name = "cities")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "city_id")
	private Long id;

	@Column(name = "name", length = 100, unique = false, nullable = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", referencedColumnName = "state_id", foreignKey = @ForeignKey(name = "Fk_city_state"))
	private State state;

	public City() {
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", state=" + state + "]";
	}

}