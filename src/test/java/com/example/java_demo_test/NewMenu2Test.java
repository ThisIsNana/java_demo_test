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
		NewMenu2 nm2 = new NewMenu2("��","��",110);
		
		// �]��ID�O�|�۰ʲ��ͪ��y�����A�ҥH�S���gid�]�|�۰ʷs�W�i�h
		newMenu2DAO.save(nm2);
	}
}
