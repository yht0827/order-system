package com.example.ordersystem.config;

import com.example.ordersystem.order.OrderRepository;
import com.example.ordersystem.order.OrderService;

public class AppConfig {

	public OrderService getOrderService() {
		return new OrderService(new OrderRepository());
	}

}