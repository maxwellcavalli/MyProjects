package br.com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.example.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByEmailAndUsername(String email, String username);
	
	User findByUsername(String username);
	
}