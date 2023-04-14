package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 加依賴Spring Boot Starter Data JPA
@Table(name = "bank") // 跟資料庫的bank做連結!

public class Bank {

	@Id
	@Column(name = "account") // 資料庫裡的account
	private String account;

	@Column(name = "pwd") // 資料庫裡的pwd
	private String pwd; // 弱點掃描，"password"會被認為很重要

	@Column(name = "amount") // 資料庫裡的amount
	private int amount;
	
	//養成習慣
	public Bank() {  
		
	}
	
	public Bank(String account, String pwd, int amount) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.amount = amount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
