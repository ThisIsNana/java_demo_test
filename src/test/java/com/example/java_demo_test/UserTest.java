package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.Users;
import com.example.java_demo_test.service.ifs.UserService;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class UserTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void addUserTest() {
		Users user = new Users(0, "AAA");
		userService.addUser(user);
		
	}
}
