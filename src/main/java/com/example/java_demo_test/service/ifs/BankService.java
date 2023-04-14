package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.vo.BankResponse;

public interface BankService {

	//增加資料
	public void addInfo(Bank bank);
	
	//取出資料 bank = 回傳型態 ; Id = 有下@Id的欄位
	public Bank getAmountById(String Id);
	
	//存款
	public BankResponse deposit(Bank bank);
	
	//提款
	public BankResponse withdraw(Bank bank);

}
