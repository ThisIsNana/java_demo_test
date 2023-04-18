package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;

public class OrderResponse {

	private List<Menu> menus; // 新增多筆
//	private Menu menu;		  // 新增單筆

	private Map<String, Integer> orderMap;

	private int totalPriceOff;

	private String message;

	public OrderResponse(List<Menu> menus, String message) {
		super();
		this.menus = menus;
		this.message = message;
	}

	public OrderResponse() {
		super();
	}

	public OrderResponse(String message) {
		super();
		this.message = message;
	}

	public OrderResponse(List<Menu> menus) {
		super();
		this.menus = menus;
	}

	public OrderResponse(Map<String, Integer> orderMap, int totalPriceOff, String message) {
		super();
		this.orderMap = orderMap;
		this.totalPriceOff = totalPriceOff;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Map<String, Integer> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<String, Integer> orderMap) {
		this.orderMap = orderMap;
	}

	public int getTotalPriceOff() {
		return totalPriceOff;
	}

	public void setTotalPriceOff(int totalPriceOff) {
		this.totalPriceOff = totalPriceOff;
	}

}
