package com.example.ordersystem.order;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

	private Long orderId;
	private String customerName;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime orderTime;
	private OrderStatus orderStatus;

	@Builder
	public Order(Long orderId, String customerName, LocalDateTime orderTime, OrderStatus orderStatus) {
		this.orderId = orderId;
		this.customerName = customerName;
		this.orderTime = orderTime;
		this.orderStatus = orderStatus;
	}

	public void updateOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
