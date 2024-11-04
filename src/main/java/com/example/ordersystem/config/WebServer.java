package com.example.ordersystem.config;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.example.ordersystem.order.OrderHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {

	public static HttpServer createServer(int port) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/api/orders", new OrderHandler());
		return server;
	}

}
