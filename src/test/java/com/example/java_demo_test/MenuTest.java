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
		menus.add(new Menu("�~��",50));
		menus.add(new Menu("�T���v",30));
		menus.add(new Menu("�j�B��",20));
		orderService.addMenu(menus);
		System.out.println("===���s�W����===");	
	}
	
	@Test
	public void getPriceByIdTest() {
		Menu menu = orderService.getPriceById("�~��");
		System.out.printf("��W�G%s�A����G%d%n",menu.getItem(),menu.getPrice());
	}

	@Test
	public void addOrderTest() {
		Map<String, Integer> orders = new HashMap<>();
		orders.put("�~��", 20);
		orders.put("�j�B��", 20);
		orders.put("�T���v", -25);
		orderService.addOrder(orders);
		System.out.println("====���¥��{====");
	}
	
	

}
