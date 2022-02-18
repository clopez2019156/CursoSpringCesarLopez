package com.bolsadeideas.springboot.app.view.xml;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.bolsadeideas.springboot.app.models.entity.Client;

@Component("index.xml")
public class ClientListXmlView extends MarshallingView{

	
	@Autowired
	public ClientListXmlView(Jaxb2Marshaller marshaller) {
		super(marshaller);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		model.remove("title");
		
		@SuppressWarnings("unchecked")
		List<Client> clientes = (List<Client>)model.get("clients");
		model.remove("clients");
		model.put("clientList", new ClientList(clientes));
		
		
		
		super.renderMergedOutputModel(model, request, response);
	}
	
	

}
