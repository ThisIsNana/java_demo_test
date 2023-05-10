package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.NewMenu2;
import com.example.java_demo_test.repository.NewMenu2DAO;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class NewMenu2Test {

	@Autowired
	private NewMenu2DAO newMenu2DAO;

	@Test
	public void addDataTest() {
		NewMenu2 nm2 = new NewMenu2("魚","炸",110);
		
		// 因為ID是會自動產生的流水號，所以沒有寫id也會自動新增進去
		newMenu2DAO.save(nm2);
	}
}
