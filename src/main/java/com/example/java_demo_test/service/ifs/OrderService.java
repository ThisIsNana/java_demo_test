package com.example.java_demo_test.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {

	public void addMenu(List<Menu> menus);
	
	public Menu getPriceById(String Id);

	public OrderResponse addOrder(Map<String,Integer> orders);
	
	
}
