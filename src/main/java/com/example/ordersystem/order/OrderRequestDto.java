package com.example.ordersystem.order;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public record OrderRequestDto(String customerName, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime orderTime, OrderStatus orderStatus) {

	public Order toEntity() {
		return Order.builder()
			.customerName(customerName)
			.orderTime(orderTime)
			.orderStatus(orderStatus)
			.build();
	}

}
