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
@Table(name = "product")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "product_id")),
		@AttributeOverride(name = "name", column = @Column(name = "name")) })
@JsonColumn(id = "product_id", name = "product_name")
public class Product extends CrudDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_sub_group_id", referencedColumnName = "product_sub_group_id", foreignKey = @ForeignKey(name = "Fk_product_sub_grp"))
	@JsonProperty("product_sub_group")
	private ProductSubGroup productSubGroup;

	@Override
	@JsonProperty("product_id")
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@JsonProperty("product_name")
	public void setName(String name) {
		super.setName(name);
	}

	public ProductSubGroup getProductSubGroup() {
		return productSubGroup;
	}

	public void setProductSubGroup(ProductSubGroup productSubGroup) {
		this.productSubGroup = productSubGroup;
	}

	@Override
	public String toString() {
		return "Product [productSubGroup=" + productSubGroup + ", getId()=" + getId() + ", getName()=" + getName()
				+ "]";
	}
}