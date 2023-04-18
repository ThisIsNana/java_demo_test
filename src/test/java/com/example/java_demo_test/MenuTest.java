package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.OrderResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class MenuTest {
	

	@Autowired
	private OrderService orderService;
	
	@Test
	public void addMenuTest(){
		List<Menu> menus = new ArrayList<>
			(Arrays.asList(new Menu("�~��",150),new Menu("�T���v",200),new Menu("�j�B��",120)));
		OrderResponse orderResponse = orderService.addMenu(menus);
		List<Menu> responseList = orderResponse.getMenus();
		System.out.println(orderResponse.getMessage());
		System.out.println("===addMenuTest����===");	
		Assert.isTrue(responseList != null,"�s�W�\�I���~");
	}
	
	@Test
	public void getPriceByIdTest() {
		Menu menu = orderService.getPriceById("�~��");
		System.out.printf("��W�G%s�A����G%d%n",menu.getItem(),menu.getPrice());
	}

	@Test
	public void addOrderTest() {
		Map<String, Integer> orders = new HashMap<>();
		orders.put("�~��", 4);
		orders.put("�j�B��", 2);
		orders.put("�T���v", 3);
		OrderResponse orderResponse = orderService.addOrder(orders);
		System.out.println(orderResponse.getMessage());
		System.out.println("====addOrderTest����====");
	}
	
	

}
