package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderRequest;
import com.example.java_demo_test.vo.OrderResponse;

import io.swagger.v3.oas.annotations.Hidden;

//import springfox.documentation.annotations.ApiIgnore;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

//	@ApiIgnore //搭配swagger使用
	@Hidden
	@PostMapping(value = "add_menus") // 給外部API(postman)看的可以用_去區分
	public OrderResponse addMenu(@RequestBody OrderRequest request) {
		return orderService.addMenu(request.getMenus());
	}

	@GetMapping(value = "get_all_menu") // 給外部API(postman)看的可以用_去區分
	public OrderResponse GetAllMenus() {
		return new OrderResponse();
	}

	@PostMapping(value = "get_menu_by_name")
	public GetMenuResponse getMenuByName(@RequestBody OrderRequest request) {
		return orderService.getMenuByName(request.getName());
	}
	
	@PostMapping(value = "update_menu_price")
	public OrderResponse updateMenuPrice(@RequestBody OrderRequest request) {
		return orderService.updateMenuPrice(request.getNewMenu());
	}
	
	@PostMapping(value = "add_order")
	public OrderResponse addOrder(@RequestBody OrderRequest request) {
		return orderService.addOrder(request.getOrders());
	}
	
	
}
