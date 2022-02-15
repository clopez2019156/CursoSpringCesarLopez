package com.bolsadeideas.springboot.app.models.DAO;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.User;

public interface IUserDAO extends CrudRepository<User, Long>{
	
	public User findByUsername(String username);

}
