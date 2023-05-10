package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class PersonInfoResponse {

	private String message;
	private PersonInfo personInfo; // ³æµ§
	private List<PersonInfo> personInfoList; //¦hµ§

	public PersonInfoResponse(PersonInfo personInfo, String message) {
		super();
		this.message = message;
		this.personInfo = personInfo;
	}
	

	public PersonInfoResponse(List<PersonInfo> personInfoList, String message) {
	
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public PersonInfoResponse() {
		super();
	}

	public PersonInfoResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PersonInfo> getPersonInfoList() {
		return personInfoList;
	}

	public void setPersonInfoList(List<PersonInfo> personInfoList) {
		this.personInfoList = personInfoList;
	}
	
	
}
