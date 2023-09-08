package com.example.java_demo_test.service.impl;

import java.time.LocalTime;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.repository.RegisterDAO;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@EnableScheduling
@Service
public class RegisterServiceImpl implements RegisterService {

	private Logger logger = LoggerFactory.getLogger(getClass()); //slf4j
	
	@Autowired
	private RegisterDAO regDAO;

//	@Transactional(readOnly = true) //注意import springframework的transaction
	@Override
	public RegisterResponse register(String account, String pwd) {

		// 防呆
		if (!StringUtils.hasText(account)) {
			return new RegisterResponse("帳號不得為空!");
		}
		if (!StringUtils.hasText(pwd)) {
			return new RegisterResponse("密碼不得為空!");
		}

		Optional<Register> op = regDAO.findById(account);
		if (op.isPresent()) {
			return new RegisterResponse("帳號已存在!");
		}

		// 設定&上傳
		Register newReg = new Register();
		newReg.setAccount(account);
		newReg.setPwd(pwd);

		regDAO.save(newReg);
		return new RegisterResponse("新增完成");
	}

	@Override
	public RegisterResponse active(String account, String pwd) {

		// 防呆
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("帳號或密碼不可空白");
		}

		// 核對帳密
		Register res = regDAO.findByAccountAndPwd(account, pwd);
		if (res == null) {
			return new RegisterResponse("帳號或密碼錯誤!");
		}

		if (res.isActive() == true) {
			return new RegisterResponse("已經驗證過了!請直接登入");
		}

		res.setActive(true);
		regDAO.save(res);
		return new RegisterResponse("啟用完成");
	}

	@Override
	public RegisterResponse login(String account, String pwd) {
		// 防呆
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("帳號或密碼不可空白");
		}
		Register res = regDAO.findByAccountAndPwdAndActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("帳號/密碼錯誤，或可能尚未驗證!");
		}
		return new RegisterResponse("登入成功");
	}

	
	//=============================================================------------------=======

	// 0616 + cache寫
	@Cacheable(value="account", key="#account")
	@Override
	public RegisterResponse getRegTime(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("請先登入!");
		}
		// 驗證
		Register res = regDAO.findByAccountAndPwdAndActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("帳號、密碼錯誤，或可能尚未驗證!");
		}

		// 顯示
		return new RegisterResponse(res.getRegTime(), "已秀出註冊時間");
	}

	//另一種寫法:讓Controller簡化，讓邏輯回歸service
	@Override
	public RegisterResponse getRegTime3(RegisterRequest request, String account, String pwd, Integer verifyCode) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("請先登入!");
		}
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("驗證碼錯誤");
		}
		Register res = regDAO.findByAccountAndPwdAndActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("請先登入!!");
		}

		// 顯示
		return new RegisterResponse(res.getRegTime(), "已秀出註冊時間");
	}
		

	@Scheduled(cron = "0/7 18,20 17 * * *")
	public void scheduleTest() {
		System.out.println("now: "+ LocalTime.now());
	}
}
