package com.example.ordersystem.order;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;

@Builder
public record OrderResponseDto(String customerName,
							   @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime orderTime,
							   OrderStatus orderStatus) {

	public static OrderResponseDto toDto(Order order) {
		return OrderResponseDto.builder()
			.customerName(order.getCustomerName())
			.orderTime(order.getOrderTime())
			.orderStatus(order.getOrderStatus())
			.build();
	}

}
