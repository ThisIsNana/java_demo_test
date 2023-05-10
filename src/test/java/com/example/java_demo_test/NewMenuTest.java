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
		NewMenu nm = new NewMenu("��", "��", 150, UUID.randomUUID());
		// �]��ID�O�|�۰ʲ��ͪ��y�����A�ҥH�S���gid�]�|�۰ʷs�W�i�h
		newMenuDAO.save(nm);
	}
}
