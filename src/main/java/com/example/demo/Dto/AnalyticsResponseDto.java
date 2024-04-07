package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AnalyticsResponseDto {

	private Long placed;
	
	private Long shipped;
	
	private Long delivered;
	
	private Long currentMonthsOrders;
	
	private Long previousMonthsOrders;
	
	private Long currentMonthsEarnings;
	
	private Long previousMonthsEarnings;
}
