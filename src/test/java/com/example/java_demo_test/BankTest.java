package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.repository.BankDAO;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class BankTest {

	@Autowired
	private BankDAO bankDAO;
	
	@Autowired
	private BankService bankService ;
	
//	@Test
//	public void addBankInfo() {
//		Bank bank = new Bank("A001","pwd0000",1000); 
//		//Entity建立完成(新增到資料庫裡bank資料表中)
//		bankDAO.save(bank); //JpaR的方法
//	}
	
	@Test
	public void addInfoTest() {
		Bank bank = new Bank("acc999","pwd99999@",2000);
		bankService.addInfo(bank);
	}
	
	@Test
	public void getAmountByIdTest() {
		Bank bank = bankService.getAmountById("acc001");
		System.out.printf("帳戶：%s，餘額：%d%n",bank.getAccount(),bank.getAmount());
	}
	
	@Test
	public void depositTest() {
		//先創建測試資料
		Bank oldBank = bankDAO.save(new Bank("acc999","pwd99999@", 2000));
		//存款--確認帳密相同
		Bank newBank = new Bank("acc999","pwd99999@", 3000);
		BankResponse response = bankService.deposit(newBank);
		//測試金額存入的是否正確.isTrue(條件,false時的錯誤訊息)
		Bank resultBank = response.getBank();		
		Assert.isTrue(resultBank.getAmount() == oldBank.getAmount() + newBank.getAmount(), "金額錯誤");
		//刪除測試資料
		bankDAO.delete(resultBank);
		
	}
	
	@Test
	public void withdrawTest() {
		//先創建測試資料
		Bank oldBank = bankDAO.save(new Bank("acc999","pwd99999@", 2000));
		//提款--確認帳密相同
		Bank newBank = new Bank("acc999","pwd99999", 1500);
		BankResponse response = bankService.withdraw(newBank);
		//測試金額存入的是否正確.isTrue(預期結果,false時的錯誤訊息)
		Bank resultBank = response.getBank();
		Assert.isTrue(resultBank.getAmount() == oldBank.getAmount() - newBank.getAmount(), "錯誤~");
		Assert.isTrue(response.getMessage().equals("提款完成!"), "提款失敗");
		System.out.printf("帳戶：%s，餘額：%d%n",resultBank.getAccount(),resultBank.getAmount());
		response.getMessage();
		System.out.println();
		//刪除測試資料
		bankDAO.delete(resultBank);
		
		//orderR：listmenu 訊息 原始價格 優惠後價格
	}
	
}
