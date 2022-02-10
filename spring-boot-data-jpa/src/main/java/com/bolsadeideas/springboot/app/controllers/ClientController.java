package com.bolsadeideas.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.service.IClientService;
import com.bolsadeideas.springboot.app.models.service.IUploadFileService;

@Controller
public class ClientController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	@Qualifier("serviceJPA")
	private IClientService clientService;

	@Autowired
	private IUploadFileService uploadFileService;


	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> viewPhoto(@PathVariable String filename) {

		Resource resource = null;
		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);

	}

	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Client client = clientService.fetchByIdWithInvoices(id); // clientService.findOne(id);
		if (client == null) {
			flash.addFlashAttribute("error", "The client does not exist in the database.");

			return "redirect:/index";
		}

		model.addAttribute("client", client);
		model.addAttribute("title", "Client Detail: " + client.getName());

		return "view";
	}

	@GetMapping({ "/index", "/" })
	public String list(Model model, Authentication authentication, HttpServletRequest request) {
		
		if(authentication!=null) {
			logger.info("Hello authenticated user, your user is: ".concat(authentication.getName()));
		}
		
		if(hasRole("ROLE_ADMIN")) {
			logger.info("Hi! ".concat(authentication.getName().concat(" You have access!")));
		}else {
			logger.info("Hi! ".concat(authentication.getName().concat(" You don't have access!")));
		}
		
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
		
		if(securityContext.isUserInRole("ADMIN")) {
			logger.info("Hi! ".concat(authentication.getName().concat(" You have access!").concat(" form class: SecurityContextHolderAwareRequestWrapper")));
		}else {
			logger.info("Hi! ".concat(authentication.getName().concat(" You don't have access!").concat(" form class: SecurityContextHolderAwareRequestWrapper")));
		}
		
		if(request.isUserInRole("ROLE_ADMIN")) {
			logger.info("Hi! ".concat(authentication.getName().concat(" You have access!").concat(" form class: HttpServletRequest")));
		}else {
			logger.info("Hi! ".concat(authentication.getName().concat(" You don't have access!").concat(" form class: HttpServletRequest")));
		}
		
		model.addAttribute("title", "client list");
		model.addAttribute("clients", clientService.findAll());

		return "index";
	}

	@GetMapping("/form")
	public String form(Map<String, Object> model) {
		Client client = new Client();
		model.put("client", client);
		model.put("title", "insert client");

		return "form";
	}

	@GetMapping("/form/{id}")
	public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Client client = null;

		if (id > 0) {
			client = clientService.findOne(id);

		} else {
			return "redirect:/index";
		}
		model.put("client", client);
		model.put("title", "Edit user");

		return "form";
	}

	@PostMapping("/form")
	public String save(@Valid Client client, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash) {

		if (result.hasErrors()) {
			model.addAttribute("title", "insert client");
			return "form";

		}

		if (!photo.isEmpty()) {
			if (client.getId() != null && client.getId() > 0 && client.getPhoto() != null
					&& client.getPhoto().length() > 0) {

				uploadFileService.delete(client.getPhoto());

			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(photo);
			} catch (IOException e) {
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "you have uploaded correctly: " + uniqueFilename);
			client.setPhoto(uniqueFilename);
		}

		clientService.save(client);

		return "redirect:index";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Client client = clientService.findOne(id);

			clientService.delete(id);
			flash.addFlashAttribute("info", "Client deleted successfully");

			if (uploadFileService.delete(client.getPhoto())) {
				flash.addFlashAttribute("info", "Photo" + client.getPhoto() + " deleted successfully");
			}

		}

		return "redirect:/index";
	}
	
	private boolean hasRole(String role) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		if(context==null) {
			return false;
		}
		
		Authentication auth = context.getAuthentication();
		
		if(auth==null) {
			return false;
			
			
		}
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				
				logger.info("Hi User! ".concat(auth.getName().concat(" Your role is: ".concat(authority.getAuthority()))));
				return true;
			}
		}
		
		return false;
	}

}
