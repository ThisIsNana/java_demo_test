package com.example.java_demo_test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;
import com.fasterxml.jackson.annotation.JsonInclude;



@RestController
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@PostMapping(value = "api/register")
	public RegisterResponse register(@RequestBody RegisterRequest request) {
		return registerService.register(request.getAccount(), request.getPwd());
	}

	@PostMapping(value = "active")
	public RegisterResponse active(@RequestBody RegisterRequest request) {
		return registerService.active(request.getAccount(), request.getPwd());

	}
	
	@PostMapping(value = "api/logout")
	public RegisterResponse logout(HttpSession session) {
		session.removeAttribute("accouunt");
		session.removeAttribute("pwd");
		
		return new RegisterResponse("登出成功");
	}
	
	/*
	 * Session可以暫存已登入的狀態
	 * 1.預設時間是30分鐘。
	 * 2.是為了不用每一次做動作就重複登入
	 * 3.會產生一組Session、session Id
	 */
	@PostMapping(value = "login")
	private RegisterResponse login(@RequestBody RegisterRequest request, HttpSession session) {
		RegisterResponse res = registerService.login(request.getAccount(), request.getPwd());
		
		if(res.getMessage().equalsIgnoreCase("登入成功")) {
			//製作Session驗證碼(每次登入就產生的驗證碼)
			double random = Math.random()*10000;
			int verifyCode = (int)Math.round(random);
			//設定session
			session.setAttribute("verifyCode", verifyCode);
			session.setAttribute("account", request.getAccount());
			session.setAttribute("pwd", request.getPwd());
			session.setMaxInactiveInterval(300); //暫存時間。單位：秒
			res.setSessionId(session.getId());
			res.setVerifyCode(verifyCode);
		}
		return res;
	}

	@PostMapping(value = "get_reg_time")
	private RegisterResponse getRegTime(@RequestBody RegisterRequest request) {
		return registerService.getRegTime(request.getAccount(), request.getPwd());

	}
	
	//寫法1:把邏輯判斷寫在controller
	@PostMapping(value = "get_reg_time_2")
	private RegisterResponse getRegTime(@RequestBody RegisterRequest request, HttpSession session) {
		
		//取得已登入的帳密
		String account = (String) session.getAttribute("account");
		String pwd = (String) session.getAttribute("pwd");
		
		//防止尚未登入
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("請先登入!");
		}
		
		//必須輸入登入產生的驗證碼
		Integer verifyCode = (Integer) session.getAttribute("verifyCode");
		
		//未登入or時效過了就會是null
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("驗證碼錯誤");
		}
		return registerService.getRegTime(account,pwd);

	}
	
	
	//寫法2:讓Controller簡化，讓邏輯回歸service
	@PostMapping(value = "get_reg_time_3")
	private RegisterResponse getRegTime3(@RequestBody RegisterRequest request, HttpSession session) {
		String account = (String) session.getAttribute("account");
		String pwd = (String) session.getAttribute("pwd");
		Integer verifyCode = (Integer) session.getAttribute("verifyCode");
		
		return registerService.getRegTime3(request, account, pwd, verifyCode);
	}

}
