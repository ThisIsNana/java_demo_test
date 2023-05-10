package com.example.java_demo_test.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {

	public OrderResponse addMenu(List<Menu> menus);

	public OrderResponse addOrder(Map<String, Integer> orders);

	public OrderResponse getAllMenus();

	public Menu getInfoById(String Id);

	public GetMenuResponse getMenuByName(String name); // name = À\ÂI¦WºÙ
	
	public OrderResponse updateMenuPrice(List<Menu> menuList);
	

}
