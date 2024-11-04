package com.example.ordersystem.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

	private static final Map<Long, Order> orderMap;
	private static Long LAST_ORDER_ID = 0L;

	static {
		orderMap = new HashMap<>();
	}

	public Order findById(Long id) {
		return orderMap.get(id);
	}

	public List<Order> findAll() {
		return new ArrayList<>(orderMap.values());
	}

	public Order save(Order order) {
		LAST_ORDER_ID++;
		orderMap.put(order.getOrderId(), order);

		return orderMap.get(order.getOrderId());
	}

	public Long getLastOrderId() {
		return LAST_ORDER_ID;
	}

}
