package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_demo_test.entity.Users;
import com.example.java_demo_test.repository.UserDAO;
import com.example.java_demo_test.service.ifs.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void addUser(Users user) {
		// 防呆
		if (user == null) {
			System.out.println("資料勿空");
		}
		List<Users> allUsers = userDAO.findAll();

		if (allUsers.size() == 0) {
			userDAO.save(user);
			
		} else {

			Users lastUser = allUsers.get(allUsers.size() - 1);

			int nowId = lastUser.getId();
			String newName = "name_";
			if (nowId < 10) {
				newName += "0" + nowId;
			} else {
				newName += nowId;
			}

			lastUser.setName(newName);
			userDAO.save(lastUser);
		}
		System.out.println("完成");
	}

}
