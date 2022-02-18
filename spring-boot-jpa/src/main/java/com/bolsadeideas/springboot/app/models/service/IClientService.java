package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.Product;

public interface IClientService {

public List<Client> findAll();
	
	public void save(Client client);
	
	public Client findOne(Long id);
	
	public void delete(Long id);

	public List<Product> findByName(String term);
	
	public void saveBill(Bill bill);
	
	public Product findProductById(Long id);
	
	public Bill findBillById(Long id);
	
	public void deleteBill(Long id);
	
	public Bill fetchByIdWithClientWithItemBillWithProduct(Long id);
	
	public Client fetchByIdWithInvoices(Long id);
}
