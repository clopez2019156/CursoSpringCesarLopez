package com.bolsaideas.springboot.web.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsaideas.springboot.web.app.models.Usuario;
@Controller
@RequestMapping("/app")
public class IndexController {

	@GetMapping({"/index","/home"})
	public String index(Model model) {
		model.addAttribute("titulo", "Hola spring framework con model!");
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Cesar");
		usuario.setApellido("López");
		usuario.setEmail("cl232326@gmail.com");
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Usuario");

		return "perfil";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de Usuarios");
		
		
		return "listar";
	
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Cesar", "Lopez", "cl232326@g,ail.com"));
		usuarios.add(new Usuario("Mario", "Hernandez", "fk@g,ail.com"));
		usuarios.add(new Usuario("Joel", "Fonseca", "jf@g,ail.com"));
		
		return usuarios;
		
	}
	
}
