package br.com.project.everest.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.project.everest.domain.State;
import br.com.project.everest.repository.base.CrudRepository;

@RepositoryRestResource(path="stateRepo", collectionResourceRel="d")
public interface StateRepository extends CrudRepository<State> {
	
	State findByAbreviation(@Param("abreviation") String abreviation);
	
}