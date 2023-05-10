package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Login;

public class LoginResponse {

	private Login login;
	private List<Login> loginList;
	private String name;
	private String message;

	public LoginResponse() {
		super();
	}

	public LoginResponse(List<Login> loginList, String message) {
		super();
		this.loginList = loginList;
		this.message = message;
	}

	public LoginResponse(String message) {
		super();
		this.message = message;
	}

	public LoginResponse(Login login) {
		super();
		this.login = login;
	}

	public LoginResponse(Login login, String message) {
		super();
		this.login = login;
		this.message = message;
	}

	public LoginResponse(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}

	public List<Login> getLoginList() {
		return loginList;
	}

	public void setLoginList(List<Login> loginList) {
		this.loginList = loginList;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
