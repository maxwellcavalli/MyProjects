package br.com.project.everest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.project.everest.domain.User;

@RepositoryRestResource(collectionResourceRel = "userRepository", path = "userRepository")
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
	User findById(@Param("id") Long id);
	
	User findByUsername(@Param("username") String username); 
	
	Page<User> findByUsernameLikeIgnoreCase(@Param("username") String username, Pageable pageable);
	
}