package com.example.java_demo_test.entity;

import java.io.Serializable;

public class NewMenu2Id implements Serializable{
		//�o��class�M����i�ƦX�D��j
		//1.����Entity��ID�ݩ�copy�L��
		//2.�ͦ�GETTER��SETTER+�غc��k
		//3.��@Serializable�åζ��L�C�۰ʲ��X��(�e��ӿﶵ�ҥi
	private static final long serialVersionUID = 1L; //�۰ʲ���

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
