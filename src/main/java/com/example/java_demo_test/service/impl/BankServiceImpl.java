package com.example.java_demo_test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.repository.BankDAO;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@Service
public class BankServiceImpl implements BankService {

	private String ptn = "\\w{3,8}";

	@Autowired
	private BankDAO bankDAO;

	@Override // 建立帳戶
	public void addInfo(Bank bank) { // 實作插入參數時，介面也要跟著改

		// 檢查帳號
		checkAcc(bank);

		// 檢查密碼
		checkPwd(bank);

		// 檢查餘額
		checkAmt(bank);

		bankDAO.save(bank);
		// Entity建立完成(新增到資料庫裡bank資料表中)
		// JpaR的方法

		System.out.println("==完成==");

	}

	// 檢查帳號：1.長度3-8、2.帳號不能有任何空白、3.帳號不能有特殊符號 =>使用正規表達式
	private void checkAcc(Bank bank) {
		// null.任何方法都會報錯!
		if (bank == null) {
			System.out.println("Bank資料為null");
			return;
		}

		String acc = bank.getAccount();
		String accPtn = "\\w{3,8}";
		if (acc == null || !acc.matches(accPtn)) {
			System.out.println("帳號有誤!請檢查~");
			return;
		}
	}

	// 檢查密碼：1.非null、2.不能有任何空白、3.長度8-16
	private void checkPwd(Bank bank) {
//		String pwdPtn = "\\S{8,16}";
		String pwdPtn = "[\\w~!@#$%^&*]{8,16}";
		String pwd = bank.getPwd();
		if (pwd == null || !pwd.matches(pwdPtn)) {
			System.out.println("密碼錯誤!請檢查~");
			return;
		}
	}

	// 檢查餘額：不得為負數
	private void checkAmt(Bank bank) {
		int amt = bank.getAmount();
		if (amt < 0) {
			System.out.println("金額不得為負數!");
			return;
		}
	}

	// ======================================================
	// 取出資料
	@Override
	public Bank getAmountById(String Id) {
		// StringUtils.hasText跟String.IsBlank很像，但為相反意思且與加判斷null。
		// 確認有輸入正確的id
		if (!StringUtils.hasText(Id)) { // ==第50行
			return new Bank();
		}

		// 確認bank裡有這個id資料，並使用Option接回
		Optional<Bank> op = bankDAO.findById(Id);
//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		//真的沒問題就GET資料--op.get方法只使用在id(PK)
//		return op.get();

		// ↓↓↓縮簡寫↓↓↓========

		// 90~95 可以用下面的句子取代
		// 有東西就回傳value(== op.get)，沒有東西就回傳orElse()裡的東西
//		Bank bank = op.orElse(new Bank());
//		return bank;

		// ↓↓↓↓更縮簡寫↓↓↓↓=====

		return op.orElse(new Bank());

	}

	@Override // 存款
	public BankResponse deposit(Bank bank) {
		// 確認輸入正確的資訊
		if (bank == null || !StringUtils.hasText(bank.getAccount()) || !StringUtils.hasText(bank.getPwd())
				|| bank.getAmount() <= 0) {
			return new BankResponse(new Bank(), "帳號、密碼或金額格式有誤。");
		}
//		//確認輸入的資訊"存在"
//		Optional<Bank> op = bankDAO.findById(bank.getAccount());
//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		Bank resultBank = op.get();
//		//確認密碼
//		if(!resultBank.getPwd().equals(bank.getPwd())) {
//			return new Bank();
//		}

		// ====↓↓使用在bankDAO裡自創ㄉ方法、用Bank接回。同時確認帳號與密碼
		Bank resultBank = bankDAO.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if (resultBank == null) {
			return new BankResponse(new Bank(), "資料不存在!帳號或密碼錯誤!");
		}

		// 存款手續
		int newAmount = resultBank.getAmount() + bank.getAmount();

		resultBank.setAmount(newAmount);
		return new BankResponse(bankDAO.save(resultBank),"存款完成!");
	}

	@Override // 提款
	public BankResponse withdraw(Bank bank) {
		// 確認輸入"正確"的資訊
		if (bank == null 
				|| !StringUtils.hasText(bank.getAccount()) 
				|| !StringUtils.hasText(bank.getPwd())
				|| bank.getAmount() <= 0) {
			return new BankResponse(new Bank(), "帳號、密碼或金額格式有誤。");
		}

		Bank resultBank = bankDAO.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if (resultBank == null) {
			return new BankResponse(new Bank(), "資料不存在!帳號或密碼錯誤!");
		}

		// 提款手續
		int newAmount = resultBank.getAmount() - bank.getAmount();
		if (newAmount < 0) {
			return new BankResponse(new Bank(), "餘額不足!");
		}
		resultBank.setAmount(newAmount);
		return new BankResponse(bankDAO.save(resultBank),"提款完成!");

	}

}
