package com.bolsadeideas.springboot.error.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.error.app.models.domain.User;

@Service
public class UserServiceImplements implements UserService {

	private List<User> read;
	
	
	public UserServiceImplements() {
		this.read = new ArrayList<>();
		this.read.add(new User(1,"Cesar","Lopez"));
		this.read.add(new User(2,"Martin","Hernandez"));
		this.read.add(new User(3,"Oscar","Juarez"));
		this.read.add(new User(4,"Franco","Ramirez"));
		this.read.add(new User(5,"Juan","Perez"));
		this.read.add(new User(6,"Mario","Aguirre"));
		this.read.add(new User(7,"David","Leon"));
	}

	@Override
	public List<User> read() {
		return this.read;
	}

	@Override
	public User getId(Integer id) {
		
		User result = null;
		
		for(User user: this.read) {
			if(user.getId().equals(id)) {
				result=user;
				break;
			}
		}
		
		return result;
	}

	@Override
	public Optional<User> GetIdOptional(Integer id) {
		User user = getId(id);
		return Optional.ofNullable(user);
	}

}
