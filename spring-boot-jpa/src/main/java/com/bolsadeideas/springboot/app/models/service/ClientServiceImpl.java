package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.DAO.IClientDAO;
import com.bolsadeideas.springboot.app.models.DAO.IInvoiceDAO;
import com.bolsadeideas.springboot.app.models.DAO.IProductDAO;
import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.Product;

@Service("serviceJPA")
public class ClientServiceImpl implements IClientService{

	@Autowired
	private IClientDAO clientDao;
	
	@Autowired
	private IProductDAO productDao;
	
	@Autowired
	private IInvoiceDAO billDao;
	
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
	@Transactional(readOnly=true)
	public List<Product> findByName(String term) {
		return productDao.findByNameLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional
	public void saveBill(Bill bill) {
		billDao.save(bill);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Product findProductById(Long id) {
		
		return productDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Bill findBillById(Long id) {
		
		return billDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteBill(Long id) {
		billDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Bill fetchByIdWithClientWithItemBillWithProduct(Long id) {
		return billDao.fetchByIdWithClientWithItemBillWithProduct(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Client fetchByIdWithInvoices(Long id) {
		return clientDao.fetchByIdWithInvoices(id);
	}

	
}
