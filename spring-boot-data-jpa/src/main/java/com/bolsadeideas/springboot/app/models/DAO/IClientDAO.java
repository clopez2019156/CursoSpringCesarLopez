package com.bolsadeideas.springboot.app.models.DAO;



import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Client;

public interface IClientDAO extends CrudRepository<Client, Long>{
	
	

	
}
