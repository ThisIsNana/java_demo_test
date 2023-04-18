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
			(Arrays.asList(new Menu("漢堡",150),new Menu("三明治",200),new Menu("大冰奶",120)));
		OrderResponse orderResponse = orderService.addMenu(menus);
		List<Menu> responseList = orderResponse.getMenus();
		System.out.println(orderResponse.getMessage());
		System.out.println("===addMenuTest結束===");	
		Assert.isTrue(responseList != null,"新增餐點錯誤");
	}
	
	@Test
	public void getPriceByIdTest() {
		Menu menu = orderService.getPriceById("漢堡");
		System.out.printf("菜名：%s，價格：%d%n",menu.getItem(),menu.getPrice());
	}

	@Test
	public void addOrderTest() {
		Map<String, Integer> orders = new HashMap<>();
		orders.put("漢堡", 4);
		orders.put("大冰奶", 2);
		orders.put("三明治", 3);
		OrderResponse orderResponse = orderService.addOrder(orders);
		System.out.println(orderResponse.getMessage());
		System.out.println("====addOrderTest結束====");
	}
	
	

}
