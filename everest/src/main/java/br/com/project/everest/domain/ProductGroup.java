package br.com.project.everest.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.project.everest.annotation.JsonColumn;
import br.com.project.everest.domain.base.CrudDomain;

@Entity
@Table(name = "product_group")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "product_group_id")),
		@AttributeOverride(name = "name", column = @Column(name = "name")) })

@JsonColumn(id = "product_group_id", name = "product_group_name")
public class ProductGroup extends CrudDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	@JsonProperty("product_group_id")
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@JsonProperty("product_group_name")
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public String toString() {
		return "ProductGroup [getId()=" + getId() + ", getName()=" + getName() + "]";
	}

}