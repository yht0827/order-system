package com.example.ordersystem.config;

import com.example.ordersystem.order.OrderRepository;
import com.example.ordersystem.order.OrderService;
import com.example.ordersystem.order.OrderServiceImpl;

public class AppConfig {

	public OrderService getOrderService() {
		return new OrderServiceImpl(new OrderRepository());
	}

}
