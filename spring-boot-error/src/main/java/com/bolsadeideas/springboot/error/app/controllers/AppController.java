package com.bolsadeideas.springboot.error.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bolsadeideas.springboot.error.app.errors.UserNotFound;
import com.bolsadeideas.springboot.error.app.models.domain.User;
import com.bolsadeideas.springboot.error.app.services.UserService;

@Controller
public class AppController {
	
	@Autowired
	private UserService userService;

	@SuppressWarnings("unused")
	@GetMapping("/index")
	public String index(){
		//Integer value = 100/0;
		//Integer value = Integer.parseInt("10x");
		return "index";
	}
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable Integer id, Model model) {
		//User user = userService.getId(id);
		
		
		User user = userService.GetIdOptional(id).orElseThrow(()-> new UserNotFound(id.toString()));
		model.addAttribute("user", user);
		model.addAttribute("title", "User Detail: ".concat(user.getName()));
		return "view";
	}
}
