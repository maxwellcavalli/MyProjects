package br.com.project.everest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.project.everest.domain.Product;
import br.com.project.everest.repository.base.CrudRepository;

@RepositoryRestResource(path="productRepo", collectionResourceRel="d")
public interface ProductRepository extends CrudRepository<Product>{
	
	@Query(value = "select product "
			+ "       from Product product "
			+ "      inner join fetch product.productSubGroup productSubGroup "
			+ "      inner join fetch productSubGroup.productGroup productGroup "
			+ "      where upper(product.name) like upper(:name) "
			+ "      order by product.name ",
			countQuery="select count(productSubGroup) "
					+ "       from Product product "
					+ "      inner join product.productSubGroup productSubGroup "
					+ "      inner join productSubGroup.productGroup productGroup "
					+ "      where upper(product.name) like upper(:name) ")
	Page<Product> findByNameLikeIgnoreCase(@Param("name") String name, Pageable pageable);

	Product findByCodeAndNotId
	
	
	
}
