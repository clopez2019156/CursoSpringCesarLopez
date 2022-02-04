package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.DAO.IClientDAO;
import com.bolsadeideas.springboot.app.models.DAO.IProductDAO;
import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.Product;

@Service("serviceJPA")
public class ClientServiceImpl implements IClientService{

	@Autowired
	private IClientDAO clientDao;
	
	@Autowired
	private IProductDAO productDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		return (List<Client>)clientDao.findAll();
	}

	@Override

	@Transactional
	public void save(Client client) {
		clientDao.save(client);
		
	}

	@Override
	@Transactional
	public Client findOne(Long id) {
		return clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientDao.deleteById(id);
		
	}

	@Override
	public List<Product> findByName(String term) {
		return productDao.findByName(term);
	}

}
