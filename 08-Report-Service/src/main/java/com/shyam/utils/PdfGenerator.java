package com.shyam.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.shyam.dto.OrderDto;

@Component
public class PdfGenerator {

	public static ByteArrayInputStream generatePdf(List<OrderDto> orders) {
		Document document=new Document();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			
			//Adding table Header
			Table table=new Table(8);
			table.setWidth(100);
			table.setPadding(3);
			Font headFont=new Font(Font.HELVETICA,12,Font.BOLD);
			table.addCell(new Phrase("Order ID",headFont));
			table.addCell(new Phrase("Order Date",headFont));
			table.addCell(new Phrase("Payment Date",headFont));
			table.addCell(new Phrase("Price",headFont));
			table.addCell(new Phrase("Product Id",headFont));
			table.addCell(new Phrase("Quantity",headFont));
			table.addCell(new Phrase("Status",headFont));
			table.addCell(new Phrase("User ID",headFont));
			
			//Adding Row
			for(OrderDto order:orders) {
				table.addCell(String.valueOf(order.getOrderId()));
				table.addCell(String.valueOf(order.getOrderDate()));
				table.addCell(order.getPaymentType());
				table.addCell(String.valueOf(order.getPrice()));
				table.addCell(String.valueOf(order.getProductId()));
				table.addCell(String.valueOf(order.getQuantity()));
				table.addCell(order.getOrderStatus().name());
				table.addCell(String.valueOf(order.getUserID()));
			}
			document.add(table);
			document.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}
