package com.example.java_demo_test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {

	@Id
	@Column(name = "account")
	private String account;

	@Column(name = "pwd")
	private String pwd;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "city")
	private String city;

	/*
	 * Private Date regTime; --> ����Date ==> new Date(); private LocalDateTime
	 * regTime; --> ����LocalDateTime ==>
	 */
	@Column(name = "register_time")
	private LocalDateTime registerTime;

	// Boolean �O��String�@�˪��w�]�Ȭ�null
	// boolean �w�]�ȬOfalse(�p�G�ڭ̨S��ʳ]�w�N�|����)
	// ��Ʈw�̧ڭ̤�ʳ]�wTINYINT��0 == false(1 == true)�A
	@Column(name = "active")
	private boolean isActive;
	// ���q�`�o�˼gis..�N�O�N��O���L�ȡA�~�ɳq�`���o�˼g

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(String account, String pwd, String name, int age, String city, LocalDateTime registerTime,
			boolean isActive) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.city = city;
		this.registerTime = registerTime;
		this.isActive = isActive;
	}
	// ====GETTER N SETTER====

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDateTime getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(LocalDateTime registerTime) {
		this.registerTime = registerTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
