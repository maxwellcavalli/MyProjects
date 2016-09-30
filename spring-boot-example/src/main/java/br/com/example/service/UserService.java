package br.com.example.service;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.example.domain.Role;
import br.com.example.domain.User;
import br.com.example.repository.UserRepository;
import br.com.example.util.HashUtil;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User save(User user) throws Exception {
		
		User userOld = userRepository.findByUsername(user.getUsername());
		if (userOld != null){
			throw new ValidationException("User Name duplicated");
		}
		
		String passHash = HashUtil.toHash(user.getPassword());
		user.setPassword(passHash);
		
		Set<Role> authorities = new HashSet<>();
		Role role = new Role();
		role.setName("USER");
		role.setUser(user);
		authorities.add(role);
		user.setAuthorities(authorities);
		
		user = userRepository.save(user);
		
		return user;
	}
	
}
