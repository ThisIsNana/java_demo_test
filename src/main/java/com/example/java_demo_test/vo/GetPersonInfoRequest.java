package com.example.java_demo_test.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetPersonInfoRequest {

	@JsonProperty("get_personinfo_list")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	@JsonProperty("get_by_age")
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@JsonProperty("get_by_age_from")
	private int age1;

	@JsonProperty("get_by_age_to")
	private int age2;

	public int getAge1() {
		return age1;
	}

	public void setAge1(int age1) {
		this.age1 = age1;
	}

	public int getAge2() {
		return age2;
	}

	public void setAge2(int age2) {
		this.age2 = age2;
	}
	

	@JsonProperty("find_containing")
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@JsonProperty("find_by_age_3")
	private int age3;
	@JsonProperty("find_by_str_1")
	private String str1;

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public int getAge3() {
		return age3;
	}

	public void setAge3(int age3) {
		this.age3 = age3;
	}

}
