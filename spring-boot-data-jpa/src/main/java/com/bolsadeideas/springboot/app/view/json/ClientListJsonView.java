package com.bolsadeideas.springboot.app.view.json;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bolsadeideas.springboot.app.models.entity.Client;

@Component("index.json")
public class ClientListJsonView extends MappingJackson2JsonView{

	@Override
	protected Object filterModel(Map<String, Object> model) {


		model.remove("title");
		@SuppressWarnings("unchecked")
		List<Client> clientes = (List<Client>)model.get("clients");
		model.remove("clients");
		model.put("clients", clientes);
		
		
		
		return super.filterModel(model);
	}
	
	

}
