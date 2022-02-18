package com.bolsadeideas.springboot.app.models.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Bill;

public interface IInvoiceDAO extends CrudRepository<Bill, Long>{

	@Query("select b from Bill b join fetch b.client c join fetch b.items i join fetch i.product where b.id=?1")
	public Bill fetchByIdWithClientWithItemBillWithProduct(Long id);
}
