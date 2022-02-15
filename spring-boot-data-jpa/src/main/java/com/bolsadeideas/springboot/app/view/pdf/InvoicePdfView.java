package com.bolsadeideas.springboot.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("bill/view")
public class InvoicePdfView extends AbstractPdfView{

	/*@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Bill bill = (Bill)model.get("bill");
		
		PdfPTable table = new PdfPTable(1);
		table.addCell("Client Data");
		table.addCell(bill.getClient().getName()+" " +bill.getClient().getLastName());
		table.addCell(bill.getClient().getEmail());

		PdfPTable table2 = new PdfPTable(1);
		table2.addCell("Invoice Data");
		table2.addCell("Invoice: ".concat(bill.getId().toString()));
		table2.addCell("Description: " + bill.getDescription());
		table2.addCell("Create At: ".concat(bill.getCreateAt().toString()));
		
		
		document.add(table);
		document.add(table2);
		
	}*/

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
Bill bill = (Bill)model.get("bill");
		
		PdfPTable table = new PdfPTable(1);
		table.addCell("Client Data");
		table.addCell(bill.getClient().getName()+" " +bill.getClient().getLastName());
		table.addCell(bill.getClient().getEmail());

		PdfPTable table2 = new PdfPTable(1);
		table2.addCell("Invoice Data");
		table2.addCell("Invoice: ".concat(bill.getId().toString()));
		table2.addCell("Description: " + bill.getDescription());
		table2.addCell("Create At: ".concat(bill.getCreateAt().toString()));
		
		
		document.add(table);
		document.add(table2);
		
	}

}
