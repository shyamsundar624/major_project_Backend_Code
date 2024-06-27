package com.shyam.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.shyam.dto.OrderDto;

@Component
public class ExcelGenerator {

	public static ByteArrayInputStream generateExcel(List<OrderDto> orders) throws IOException {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Orders");

			// Header Row
			String[] headers = { "Order Id", "Order Date", "Payment Type", "Price", "Product Id", "Quantity", "Status",
					"User Id" };

			Row headerRow = sheet.createRow(0);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillBackgroundColor((short) 12);

for(int i=0;i<headers.length;i++) {
	Cell cell = headerRow.createCell(i);
	cell.setCellValue(headers[i]);
	cell.setCellStyle(headerCellStyle);
}

//Data Rows
int rowIndex=1;

for(OrderDto order:orders) {
	Row row = sheet.createRow(rowIndex++);
	row.createCell(0).setCellValue(order.getOrderId());
	row.createCell(1).setCellValue(order.getOrderDate());
	row.createCell(2).setCellValue(order.getPaymentType());
	row.createCell(3).setCellValue(order.getPrice());
	row.createCell(4).setCellValue(order.getProductId());
	row.createCell(5).setCellValue(order.getQuantity());
	row.createCell(6).setCellValue(order.getOrderStatus().name());
	row.createCell(7).setCellValue(order.getUserID());
	
}
		workbook.write(out);
		return new ByteArrayInputStream(out.toByteArray());
		} 
	}
}
