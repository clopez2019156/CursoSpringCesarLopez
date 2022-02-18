package com.bolsadeideas.springboot.app.view.pdf;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.ItemBill;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("bill/view")
public class InvoicePdfView extends AbstractPdfView {

	/*
	 * @Override protected void buildPdfDocument(Map<String, Object> model, Document
	 * document, PdfWriter writer, HttpServletRequest request, HttpServletResponse
	 * response) throws Exception {
	 * 
	 * Bill bill = (Bill)model.get("bill");
	 * 
	 * PdfPTable table = new PdfPTable(1); table.addCell("Client Data");
	 * table.addCell(bill.getClient().getName()+" "
	 * +bill.getClient().getLastName()); table.addCell(bill.getClient().getEmail());
	 * 
	 * PdfPTable table2 = new PdfPTable(1); table2.addCell("Invoice Data");
	 * table2.addCell("Invoice: ".concat(bill.getId().toString()));
	 * table2.addCell("Description: " + bill.getDescription());
	 * table2.addCell("Create At: ".concat(bill.getCreateAt().toString()));
	 * 
	 * 
	 * document.add(table); document.add(table2);
	 * 
	 * }
	 */

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Bill bill = (Bill) model.get("bill");

		PdfPTable table = new PdfPTable(1);
		table.setSpacingAfter(20);
		
		PdfPCell cell= null;
		
		cell=new PdfPCell(new Phrase("Client Data"));
		cell.setBackgroundColor(new Color(184,218,255));
		cell.setPadding(8f);
		table.addCell(cell);
		
		table.addCell(bill.getClient().getName() + " " + bill.getClient().getLastName());
		table.addCell(bill.getClient().getEmail());

		
		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(20);
		
		cell=new PdfPCell(new Phrase("Invoice Data"));
		cell.setBackgroundColor(new Color(195,230,203));
		cell.setPadding(8f);
		table2.addCell(cell);
		
		table2.addCell("Invoice: ".concat(bill.getId().toString()));
		table2.addCell("Description: " + bill.getDescription());
		table2.addCell("Create At: ".concat(bill.getCreateAt().toString()));

		document.add(table);
		document.add(table2);

		PdfPTable table3 = new PdfPTable(4);
		table3.setWidths(new float[] {2.5f,1,1,1});
		table3.setSpacingAfter(20);
		table3.addCell("Product");
		table3.addCell("Price");
		table3.addCell("Amount ");
		table3.addCell("Total ");

		for (ItemBill item : bill.getItems()) {
			table3.addCell(item.getProduct().getName());
			table3.addCell(item.getProduct().getPrice().toString());
			table3.addCell(item.getAmount().toString());
			table3.addCell(item.calculateAmount().toString());
		}
		cell = new PdfPCell(new Phrase("Total: "));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

		table3.addCell(cell);

		table3.addCell(bill.getTotal().toString());

		document.add(table3);

	}

}
