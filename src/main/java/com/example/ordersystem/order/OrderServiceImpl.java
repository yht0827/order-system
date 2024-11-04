package com.example.ordersystem.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ordersystem.config.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderResponseDto getOne(Long id) {
		Order order = orderRepository.findById(id);

		if (null == order) {
			throw new CustomException("없는 주문입니다.");
		}

		return OrderResponseDto.toDto(order);
	}

	public List<OrderResponseDto> getAll() {
		return orderRepository.findAll().stream().map(OrderResponseDto::toDto).toList();
	}

	public Long create(OrderRequestDto orderRequestDto) {
		Order order = orderRequestDto.toEntity();

		Long orderId = orderRepository.getLastOrderId();
		order.updateOrderId(orderId + 1L);

		return orderRepository.save(order).getOrderId();
	}

}
