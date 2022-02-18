package com.bolsadeideas.springboot.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false)String error,@RequestParam(value="logout", required=false) String log,Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal!=null) {
			flash.addFlashAttribute("info", "I had already logged in before");
			return "redirect:/";
		}
		
		if(error!=null) {
			model.addAttribute("errorLogin", "Username or password incorrect");
		}
		
		if(log!=null) {
			model.addAttribute("log", "You have logged out successfully");
		}
		
		return "login";
	}

}
