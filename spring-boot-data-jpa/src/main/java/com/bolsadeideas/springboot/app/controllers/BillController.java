package com.bolsadeideas.springboot.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.ItemBill;
import com.bolsadeideas.springboot.app.models.entity.Product;
import com.bolsadeideas.springboot.app.models.service.ClientServiceImpl;


@Controller
@RequestMapping("/bill")
@SessionAttributes("bill")
public class BillController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ClientServiceImpl clientService;

	@GetMapping("/form/{id}")
	public String create(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Client client = clientService.findOne(id);
		if (client == null) {
			flash.addFlashAttribute("error", "The client does not exist in the database.");
			return "redirect:/index";
		}

		Bill bill = new Bill();
		bill.setClient(client);

		model.addAttribute("bill", bill);
		model.addAttribute("title", "Create Bill");

		return "bill/form";
	}

	@GetMapping(value = "/upload_products/{term}", produces = { "application/json" })
	public @ResponseBody List<Product> loadProducts(@PathVariable String term, Model model) {

		return clientService.findByName(term);

	}

	@PostMapping("/form")
	public String save(@Valid Bill bill, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "amount[]", required = false) Integer[] amount, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("title", "Create Bill");

			return "bill/form";

		}

		if (itemId == null || itemId.length == 0) {

			model.addAttribute("title", "Create Bill");
			model.addAttribute("error", "The invoice needs products to be registered");
			return "bill/form";
		}

		for (int i = 0; i < itemId.length; i++) {
			Product product = clientService.findProductById(itemId[i]);
			ItemBill line = new ItemBill();
			line.setAmount(amount[i]);
			line.setProduct(product);
			bill.addItemBill(line);

			log.info("iD: " + itemId[i].toString() + ", amount: " + amount[i].toString());
		}

		clientService.saveBill(bill);

		status.setComplete();
		flash.addFlashAttribute("success", "Invoice created successfully");

		return "redirect:/view/" + bill.getClient().getId();
	}
	
	
	
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable Long id, Model model,
			RedirectAttributes flash) {
	    Bill bill = clientService.fetchByIdWithClientWithItemBillWithProduct(id); //clientService.findBillById(id);
	    if(bill==null) {
	    	flash.addFlashAttribute("error", "The invoice does not exists in the database");
	    	return "redirect:/index";
	    }
		model.addAttribute("title", "Invoice: ".concat(bill.getDescription()));
		model.addAttribute("bill", bill);
		
		return "bill/view";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model, RedirectAttributes flash) {
		
		Bill bill = clientService.findBillById(id);
		
		if(bill!=null) {
			
			clientService.deleteBill(id);
			flash.addFlashAttribute("success", "invoice deleted successfully");
			return "redirect:/view/"+bill.getClient().getId();
		}
		flash.addFlashAttribute("error", "the invoice does not exist in the database, it could not be deleted");
		return "redirect:/index";
	}

}
