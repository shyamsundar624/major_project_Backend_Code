package com.shyam.dto;

import java.time.LocalDate;

import com.shyam.entity.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportDto {

	private OrderStatus status;
	private LocalDate startDate;
	private LocalDate endDate;
	
}
