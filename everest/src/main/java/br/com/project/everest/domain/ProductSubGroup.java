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
@Table(name = "product_sub_group")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "product_sub_group_id")),
		@AttributeOverride(name = "name", column = @Column(name = "name")) })

@JsonColumn(id = "product_sub_group_id", name = "product_sub_name")
public class ProductSubGroup extends CrudDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_group_id", referencedColumnName = "product_group_id", foreignKey = @ForeignKey(name = "Fk_prod_subgrd_prod_grd"))
	@JsonProperty("product_sub_group_group")
	private ProductGroup productGroup;

	@Override
	@JsonProperty("product_sub_group_id")
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@JsonProperty("product_sub_name")
	public void setName(String name) {
		super.setName(name);
	}

	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}

	@Override
	public String toString() {
		return "ProductSubGroup [productGroup=" + productGroup + ", getId()=" + getId() + ", getName()=" + getName()
				+ "]";
	}

}