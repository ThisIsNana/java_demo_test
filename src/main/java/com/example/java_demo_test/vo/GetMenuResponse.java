package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Optional;

import com.example.java_demo_test.entity.Menu;

public class GetMenuResponse {

	public Menu menu;

	public String message;

	public GetMenuResponse() {
		super();
	}

	public GetMenuResponse(String message) {
		super();
		this.message = message;
	}

	public GetMenuResponse(Menu menu, String message) {
		super();
		this.menu = menu;
		this.message = message;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
