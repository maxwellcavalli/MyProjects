package br.com.project.everest.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.project.everest.domain.base.CrudDomain;

@NoRepositoryBean
public interface CrudRepository<T extends CrudDomain> extends PagingAndSortingRepository<T, Long>{
	
	T findById(@Param("id") Long id);
	
	T findByName(@Param("name") String name);
	
	Page<T> findByNameLikeIgnoreCase(@Param("name") String name, Pageable pageable);
	
} 