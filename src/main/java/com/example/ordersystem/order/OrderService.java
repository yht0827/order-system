package com.example.ordersystem.order;

import java.util.List;

public interface OrderService {

	OrderResponseDto getOne(Long id);

	List<OrderResponseDto> getAll();

	Long create(OrderRequestDto orderRequestDto);
}
