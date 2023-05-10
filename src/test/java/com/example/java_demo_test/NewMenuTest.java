package com.example.java_demo_test;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.NewMenu;
import com.example.java_demo_test.repository.NewMenuDAO;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class NewMenuTest {

	@Autowired
	private NewMenuDAO newMenuDAO;

	@Test
	public void addDataTest() {
		NewMenu nm = new NewMenu("牛", "炸", 150, UUID.randomUUID());
		// 因為ID是會自動產生的流水號，所以沒有寫id也會自動新增進去
		newMenuDAO.save(nm);
	}
}
