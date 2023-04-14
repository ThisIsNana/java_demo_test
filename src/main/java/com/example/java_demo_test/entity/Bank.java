package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // �[�̿�Spring Boot Starter Data JPA
@Table(name = "bank") // ���Ʈw��bank���s��!

public class Bank {

	@Id
	@Column(name = "account") // ��Ʈw�̪�account
	private String account;

	@Column(name = "pwd") // ��Ʈw�̪�pwd
	private String pwd; // �z�I���y�A"password"�|�Q�{���ܭ��n

	@Column(name = "amount") // ��Ʈw�̪�amount
	private int amount;
	
	//�i���ߺD
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
