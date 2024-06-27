package com.shyam.rest;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.dto.OrderDto;
import com.shyam.dto.ReportDto;
import com.shyam.exception.ReportsServiceException;
import com.shyam.service.ReportService;
import com.shyam.utils.ExcelGenerator;
import com.shyam.utils.PdfGenerator;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/reports")
public class ReportRestController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/FilterOrderExcel1")
	public ResponseEntity<InputStreamResource> downloadExcel(@RequestBody ReportDto reportDto) {
		List<OrderDto> orders = new ArrayList<>();
		if (reportDto.getStatus() != null) {
			orders = reportService.orderByStatus(reportDto.getStatus());
		}

		if (reportDto.getStartDate() != null || reportDto.getEndDate() != null) {
			List<OrderDto> dateFilteredOrders = reportService.getOrdersBetweenDate(reportDto.getStartDate(),
					reportDto.getEndDate());

			if (!orders.isEmpty()) {
				orders.retainAll(dateFilteredOrders);
			} else {
				orders = dateFilteredOrders;
			}
		}
		if (reportDto.getStatus() == null && reportDto.getStartDate() == null && reportDto.getEndDate() == null) {
			orders = reportService.getAllOrders();
		}

		if (orders != null) {
			try {
				ByteArrayInputStream in = ExcelGenerator.generateExcel(orders);
				InputStreamResource resource = new InputStreamResource(in);
				HttpHeaders headers = new HttpHeaders();
				return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(resource);

			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	
	@GetMapping("/FilterOrderPdf")
	
	public ResponseEntity<InputStreamResource> downloadPdf(ReportDto reportDto) throws ReportsServiceException
	{
		List<OrderDto> orders=new ArrayList<>();
		
		if(reportDto.getStatus()!=null) {
			orders=reportService.orderByStatus(reportDto.getStatus());
		}
		
		if(reportDto.getStartDate()!=null || reportDto.getEndDate()!=null) {
			List<OrderDto> dateFilteredOrder = reportService.getOrdersBetweenDate(reportDto.getStartDate(),reportDto.getEndDate());
		
		if(!orders.isEmpty()) {
			orders.retainAll(dateFilteredOrder);
		}else {
			orders=dateFilteredOrder;
		}
		}
		
		if(reportDto.getStatus()==null && reportDto.getStartDate()==null && reportDto.getEndDate()==null) {
			orders=reportService.getAllOrders();
		}
		
		if(orders!=null) {
			ByteArrayInputStream in=PdfGenerator.generatePdf(orders);
			InputStreamResource resource=new InputStreamResource(in);
			HttpHeaders headers=new HttpHeaders();
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
}
