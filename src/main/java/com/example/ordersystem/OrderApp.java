package com.example.ordersystem;

import java.io.IOException;

import com.example.ordersystem.config.WebServer;
import com.sun.net.httpserver.HttpServer;

public class OrderApp {

	public static void main(String[] args) {
		try {
			HttpServer server = WebServer.createServer(8080);
			server.start();
			System.out.println("서버가 시작되었습니다. 포트: 8080");
		} catch (IOException e) {
			System.err.println("서버 시작 중 오류 발생: " + e.getMessage());
		}
	}

}
