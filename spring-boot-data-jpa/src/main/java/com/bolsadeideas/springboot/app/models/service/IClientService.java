package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.Product;

public interface IClientService {

public List<Client> findAll();
	
	public void save(Client client);
	
	public Client findOne(Long id);
	
	public void delete(Long id);

	public List<Product> findByName(String term);
	
}
