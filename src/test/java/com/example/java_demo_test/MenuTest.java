package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.service.ifs.OrderService;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class MenuTest {
	

	@Autowired
	private OrderService orderService;
	
	@Test
	public void addMenuTest(){
		List<Menu> menus = new ArrayList<>();
		menus.add(new Menu("漢堡",50));
		menus.add(new Menu("三明治",30));
		menus.add(new Menu("大冰奶",20));
		orderService.addMenu(menus);
		System.out.println("===菜單新增結束===");	
	}
	
	@Test
	public void getPriceByIdTest() {
		Menu menu = orderService.getPriceById("漢堡");
		System.out.printf("菜名：%s，價格：%d%n",menu.getItem(),menu.getPrice());
	}

	@Test
	public void addOrderTest() {
		Map<String, Integer> orders = new HashMap<>();
		orders.put("漢堡", 20);
		orders.put("大冰奶", 20);
		orders.put("三明治", -25);
		orderService.addOrder(orders);
		System.out.println("====謝謝光臨====");
	}
	
	

}
