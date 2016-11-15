package br.com.project.everest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.project.everest.domain.ProductSubGroup;
import br.com.project.everest.repository.base.CrudRepository;

@RepositoryRestResource(path="productSubGroupRepo", collectionResourceRel="d")
public interface ProductSubGroupRepository extends CrudRepository<ProductSubGroup>{
	
	@Query(value = "select productSubGroup "
			+ "       from ProductSubGroup productSubGroup "
			+ "      inner join fetch productSubGroup.productGroup productGroup "
			+ "      where upper(productSubGroup.name) like upper(:name) "
			+ "      order by productSubGroup.name ",
			countQuery="select count(productSubGroup) "
					+ "       from ProductSubGroup productSubGroup "
					+ "      inner join productSubGroup.productGroup productGroup "
					+ "      where upper(productSubGroup.name) like upper(:name) ")
	Page<ProductSubGroup> findByNameLikeIgnoreCase(@Param("name") String name, Pageable pageable);
	
	
	@Query(value = "select productSubGroup "
			+ "       from ProductSubGroup productSubGroup "
			+ "      inner join fetch productSubGroup.productGroup productGroup "
			+ "      where upper(productSubGroup.name) like upper(:name) "
			+ "        and upper(productGroup.name) like upper(:group) "
			+ "      order by productGroup.name, productSubGroup.name ",
			countQuery="select count(productSubGroup) "
					+ "       from ProductSubGroup productSubGroup "
					+ "      inner join productSubGroup.productGroup productGroup "
					+ "      where upper(productSubGroup.name) like upper(:name) "
					+ "        and upper(productGroup.name) like upper(:group) ")
	Page<ProductSubGroup> findByNameAndGroupLikeIgnoreCase(@Param("name") String name, 
			@Param("group") String group, Pageable pageable);
	
	
}