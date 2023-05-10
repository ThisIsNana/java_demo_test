package com.example.java_demo_test.entity;

import java.io.Serializable;

public class NewMenu2Id implements Serializable{
		//這個class專門放【複合主鍵】
		//1.先把Entity的ID屬性copy過來
		//2.生成GETTER跟SETTER+建構方法
		//3.實作Serializable並用黃蚯蚓自動產出↓(前兩個選項皆可
	private static final long serialVersionUID = 1L; //自動產生

	private String category;

	private String cookWay;

	public NewMenu2Id() {
		super();
	}

	public NewMenu2Id(String category, String cookWay) {
		super();
		this.category = category;
		this.cookWay = cookWay;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCookWay() {
		return cookWay;
	}

	public void setCookWay(String cookWay) {
		this.cookWay = cookWay;
	}

}
