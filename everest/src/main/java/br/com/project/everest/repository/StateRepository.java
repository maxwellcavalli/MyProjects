package br.com.project.everest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.project.everest.domain.State;

@RepositoryRestResource(collectionResourceRel = "stateRepository", path = "stateRepository")
public interface StateRepository extends PagingAndSortingRepository<State, Long>{
	
	State findById(@Param("id") Long id);
	
	State findByName(@Param("name") String name);
	
	State findByAbreviation(@Param("abreviation") String abreviation);
	
	Page<State> findByNameLikeIgnoreCase(@Param("name") String name, Pageable pageable);
	
}