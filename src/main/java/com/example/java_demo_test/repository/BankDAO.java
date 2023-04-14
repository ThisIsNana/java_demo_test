package com.example.java_demo_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Bank;
//數據訪問層
@Repository
public interface BankDAO extends JpaRepository<Bank, String> {  
	// <T,ID> T是Entity裡的Bank，ID是PK
	
	public Bank findByAccountAndPwd(String account,String pwd);
	//注意，這裡會自動套用寫程式命名的變數名稱(非資料庫的column名稱)
}
