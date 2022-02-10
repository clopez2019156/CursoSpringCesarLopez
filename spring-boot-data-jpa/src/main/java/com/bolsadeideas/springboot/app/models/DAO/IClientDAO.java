package com.bolsadeideas.springboot.app.models.DAO;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Client;

public interface IClientDAO extends CrudRepository<Client, Long>{
	
	@Query("select c from Client c left join fetch c.invoices i where c.id=?1")
	public Client fetchByIdWithInvoices(Long id);

	
}
