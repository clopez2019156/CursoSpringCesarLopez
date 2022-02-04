package com.bolsadeideas.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.Product;
import com.bolsadeideas.springboot.app.models.service.ClientServiceImpl;

@Controller
@RequestMapping("/bill")
@SessionAttributes("bill")
public class BillController {

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
	
	@GetMapping(value="/upload_products/{term}", produces= {"aplication/json"})
	public @ResponseBody List<Product> loadProducts(@PathVariable String term, Model model){
		
		return clientService.findByName(term);
		
	}

}
