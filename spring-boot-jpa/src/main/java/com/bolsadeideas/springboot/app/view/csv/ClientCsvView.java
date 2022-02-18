package com.bolsadeideas.springboot.app.view.csv;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.bolsadeideas.springboot.app.models.entity.Client;

@Component("index.csv")
public class ClientCsvView extends AbstractView{

	
	
	public ClientCsvView() {

		setContentType("text/csv");
	}

	@Override
	protected boolean generatesDownloadContent() {
		
		return true;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"clients.csv\"");
		response.setContentType(getContentType());
		
		@SuppressWarnings("unchecked")
		List<Client> clients = (List<Client>)model.get("clients");
		ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] header = {"id","name","lastName","email","createAt"};
		
		for(Client client: clients) {
			beanWriter.write(client, header);
		}
		
		beanWriter.close();
		
	}
	
	

}