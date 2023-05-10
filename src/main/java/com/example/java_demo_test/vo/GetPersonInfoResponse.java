package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class GetPersonInfoResponse {

	private String message;
	private PersonInfo personInfo;
	private List<PersonInfo> personInfoList;
	private int age;

	public GetPersonInfoResponse() {
		super();
	}

	public GetPersonInfoResponse(String message) {
		super();
		this.message = message;
	}

	public GetPersonInfoResponse(PersonInfo personInfo, String message) {
		super();
		this.message = message;
		this.personInfo = personInfo;
	}

	public GetPersonInfoResponse(List<PersonInfo> personInfoList, String message) {
		super();
		this.message = message;
		this.personInfoList = personInfoList;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public List<PersonInfo> getPersonInfoList() {
		return personInfoList;
	}

	public void setPersonInfoList(List<PersonInfo> personInfoList) {
		this.personInfoList = personInfoList;
	}

}
