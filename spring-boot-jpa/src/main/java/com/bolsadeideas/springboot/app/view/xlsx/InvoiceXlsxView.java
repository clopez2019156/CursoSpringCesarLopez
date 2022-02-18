package com.bolsadeideas.springboot.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.ItemBill;

@Component("bill/view.xlsx")
public class InvoiceXlsxView extends AbstractXlsxView

{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		response.setHeader("Content-Disposition", "attachment; filename=\"Invoice_view.xlsx\"");

		Bill bill =(Bill) model.get("bill");
		Sheet sheet = workbook.createSheet();
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Client Data");
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(bill.getClient().getName()+" "+bill.getClient().getLastName());
		
		row=sheet.createRow(2);
		cell=row.createCell(0);
		cell.setCellValue(bill.getClient().getEmail());
		
		sheet.createRow(4).createCell(0).setCellValue("Invoice Data");
		sheet.createRow(5).createCell(0).setCellValue("Invoice: "+bill.getId());
		sheet.createRow(6).createCell(0).setCellValue("Description: "+bill.getDescription());
		sheet.createRow(7).createCell(0).setCellValue("Create at: "+bill.getCreateAt());
		
		
		CellStyle theaderStyle=workbook.createCellStyle();
		theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderStyle.setBorderTop(BorderStyle.MEDIUM);
		theaderStyle.setBorderRight(BorderStyle.MEDIUM);
		theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderStyle.setFillForegroundColor(IndexedColors.GREEN.index);
		theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle tbodyStyle = workbook.createCellStyle();
		tbodyStyle.setBorderBottom(BorderStyle.THIN);
		tbodyStyle.setBorderTop(BorderStyle.THIN);
		tbodyStyle.setBorderRight(BorderStyle.THIN);
		tbodyStyle.setBorderLeft(BorderStyle.THIN);
		
		
		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue("Product");
		header.createCell(1).setCellValue("Price");
		header.createCell(2).setCellValue("Amount");
		header.createCell(3).setCellValue("Total");
		
		header.getCell(0).setCellStyle(theaderStyle);
		header.getCell(1).setCellStyle(theaderStyle);
		header.getCell(2).setCellStyle(theaderStyle);
		header.getCell(3).setCellStyle(theaderStyle);
		
		int rownum=10;
		
		for(ItemBill item: bill.getItems()) {

			Row fila=sheet.createRow(rownum++);
			cell=fila.createCell(0);
			cell.setCellValue(item.getProduct().getName());
			cell.setCellStyle(tbodyStyle);
			
			cell=fila.createCell(1);
			fila.createCell(1).setCellValue(item.getProduct().getPrice());
			cell.setCellStyle(tbodyStyle);
			
			cell=fila.createCell(2);
			fila.createCell(2).setCellValue(item.getAmount());
			cell.setCellStyle(tbodyStyle);
			
			cell=fila.createCell(3);
			fila.createCell(3).setCellValue(item.calculateAmount());
			cell.setCellStyle(tbodyStyle);
			
		}
		
		Row total = sheet.createRow(rownum);
		cell=total.createCell(2);
		cell.setCellValue("Grand Total");
		
		total.createCell(3).setCellValue(bill.getTotal());
		cell.setCellStyle(theaderStyle);
	}

}
