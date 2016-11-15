package br.com.project.everest.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.project.everest.annotation.JsonColumn;
import br.com.project.everest.domain.base.CrudDomain;

@Entity
@Table(name = "customer")
@AttributeOverrides({ 
	@AttributeOverride(name = "id", column = @Column(name = "customer_id")),
	@AttributeOverride(name = "name", column = @Column(name = "name")) })
@JsonColumn(id = "customer_id", name = "customer_name")
public class Customer extends CrudDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", referencedColumnName = "district_id", foreignKey = @ForeignKey(name = "FK_customer_district"))
	@JsonProperty("customer_district")
	private District district;

	@Column(name = "address", length = 100, nullable = true)
	private String address;

	@JsonProperty("customer_contacts")
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, mappedBy = "customer", orphanRemoval=true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	            org.hibernate.annotations.CascadeType.DELETE,
	            org.hibernate.annotations.CascadeType.MERGE,
	            org.hibernate.annotations.CascadeType.PERSIST})
	private Set<CustomerContact> customerContacts;

	@Override
	@JsonProperty("customer_id")
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@JsonProperty("customer_name")
	public void setName(String name) {
		super.setName(name);
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<CustomerContact> getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(Set<CustomerContact> customerContacts) {
		this.customerContacts = customerContacts;
	}

}