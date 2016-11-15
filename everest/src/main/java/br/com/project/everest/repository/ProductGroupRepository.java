package br.com.project.everest.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.project.everest.domain.ProductGroup;
import br.com.project.everest.repository.base.CrudRepository;

@RepositoryRestResource(path="productGroupRepo", collectionResourceRel="d")
public interface ProductGroupRepository extends CrudRepository<ProductGroup>{
	
}