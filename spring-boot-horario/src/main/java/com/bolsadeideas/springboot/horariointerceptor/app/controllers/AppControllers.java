package com.bolsadeideas.springboot.horariointerceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppControllers {
	
	@Value("${config.schedule.opening}")
	private Integer opening;
	
	@Value("${config.schedule.closing}")
	private Integer closing;
	
	@GetMapping({"/","/index"})
	public String index(Model model) {

		model.addAttribute("title", "welcome to business hours");
		return "index";
	}
	
	@GetMapping("/close")
	public String close(Model model) {
		
		StringBuilder message = new StringBuilder("Closed, please visit us from ");
		message.append(opening);
		message.append(" hrs to ");
		message.append(closing);
		message.append(" hrs.");
		model.addAttribute("title", "Outside business hours");
		model.addAttribute("message", message.toString());
		return "close";
	}

}
