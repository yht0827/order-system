package com.example.ordersystem.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {

	PROCESSING("processing"),
	SHIPPING("shipping"),
	COMPLETED("completed");

	@JsonCreator
	public static OrderStatus from(String value) {
		for (OrderStatus status : OrderStatus.values()) {
			if (status.getValue().equals(value)) {
				return status;
			}
		}
		return null;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	private final String value;

}
