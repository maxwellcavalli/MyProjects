package br.com.project.everest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.project.everest.domain.base.CrudDomainUtil;
import br.com.project.everest.type.ContactType;

@Entity
@Table(name = "customer_contact")
public class CustomerContact extends CrudDomainUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_contact_id")
	@JsonProperty(value = "customer_contact_id")
	private Long id;

	@NotNull
	@Column(name = "contact_name", length = 100, nullable = false)
	@JsonProperty(value = "customer_contact_name")
	private String contactName;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "contact_type", columnDefinition = "smallint", nullable=false)
	@JsonProperty(value = "customer_contact_type")
	private ContactType contactType;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_cust_contac_customer"), nullable=false)
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerContact [id=" + id + ", contactName=" + contactName + ", contactType=" + contactType
				+ ", customer=" + customer + "]";
	}

}