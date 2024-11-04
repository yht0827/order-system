package com.example.ordersystem.order;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.example.ordersystem.config.AppConfig;
import com.example.ordersystem.config.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class OrderHandler implements HttpHandler {
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final AppConfig appConfig = new AppConfig();

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String path = exchange.getRequestURI().getPath();
		String requestMethod = exchange.getRequestMethod();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		if ("POST".equalsIgnoreCase(requestMethod) && "/api/orders".equals(path)) {
			handlePostOrder(exchange);
		} else if ("GET".equalsIgnoreCase(requestMethod) && path.matches("/api/orders/\\d+")) {
			handleGetOrder(exchange, path);
		} else if ("GET".equalsIgnoreCase(requestMethod) && "/api/orders/list".equals(path)) {
			handleGetOrderList(exchange);
		} else {
			sendResponse(exchange, 405, "{\"error\": \"지원하지 않는 메소드입니다.\"}");
		}
	}

	private void handleGetOrder(HttpExchange exchange, String path) throws IOException {

		String idString = path.substring(path.lastIndexOf('/') + 1);
		Long id = Long.parseLong(idString);

		OrderResponseDto orderResponseDto = null;
		try {
			orderResponseDto = appConfig.getOrderService().getOne(id);
		} catch (CustomException e) {
			sendResponse(exchange, e.getStatusCode(), "{\"error\": \"" + e.getMessage() + "\"}");
		}

		String jsonResponse = objectMapper.writeValueAsString(orderResponseDto);
		sendResponse(exchange, 200, jsonResponse);
	}

	private void handleGetOrderList(HttpExchange exchange) throws IOException {
		List<OrderResponseDto> orderResponseDtoList = appConfig.getOrderService().getAll();

		String jsonResponse = "{\"data\": " + objectMapper.writeValueAsString(orderResponseDtoList) + "}";
		sendResponse(exchange, 200, jsonResponse);
	}

	private void handlePostOrder(HttpExchange exchange) throws IOException {
		OrderRequestDto orderRequestDto = readOrderFromRequest(exchange);
		Long id = appConfig.getOrderService().create(orderRequestDto);

		System.out.println(id);

		String jsonResponse = "{\"message\": \"주문 데이터 저장\", \"id\": " + id + "}";
		sendResponse(exchange, 200, jsonResponse);
	}

	private OrderRequestDto readOrderFromRequest(HttpExchange exchange) throws IOException {
		InputStream inputStream = exchange.getRequestBody();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder requestBody = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			requestBody.append(line);
		}

		return objectMapper.readValue(requestBody.toString(), OrderRequestDto.class);
	}

	private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
		exchange.getResponseHeaders().set("Content-Type", "application/json");
		exchange.sendResponseHeaders(statusCode, response.length());

		try (BufferedOutputStream bos = new BufferedOutputStream(exchange.getResponseBody())) {
			bos.write(response.getBytes(StandardCharsets.UTF_8));
			bos.flush();
		}
	}
}