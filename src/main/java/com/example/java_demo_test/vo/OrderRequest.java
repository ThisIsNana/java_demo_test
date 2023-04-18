package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {

	@JsonProperty("menu_list")
	List<Menu> menus;

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@JsonProperty("order_info")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("update_price_list")
	List<Menu> newMenu;

	public List<Menu> getNewMenu() {
		return newMenu;
	}

	public void setNewMenu(List<Menu> newMenu) {
		this.newMenu = newMenu;
	}
	

}
