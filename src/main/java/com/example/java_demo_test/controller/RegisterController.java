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
		
		return new RegisterResponse("�n�X���\");
	}
	
	/*
	 * Session�i�H�Ȧs�w�n�J�����A
	 * 1.�w�]�ɶ��O30�����C
	 * 2.�O���F���ΨC�@�����ʧ@�N���Ƶn�J
	 * 3.�|���ͤ@��Session�Bsession Id
	 */
	@PostMapping(value = "login")
	private RegisterResponse login(@RequestBody RegisterRequest request, HttpSession session) {
		RegisterResponse res = registerService.login(request.getAccount(), request.getPwd());
		
		if(res.getMessage().equalsIgnoreCase("�n�J���\")) {
			//�s�@Session���ҽX(�C���n�J�N���ͪ����ҽX)
			double random = Math.random()*10000;
			int verifyCode = (int)Math.round(random);
			//�]�wsession
			session.setAttribute("verifyCode", verifyCode);
			session.setAttribute("account", request.getAccount());
			session.setAttribute("pwd", request.getPwd());
			session.setMaxInactiveInterval(300); //�Ȧs�ɶ��C���G��
			res.setSessionId(session.getId());
			res.setVerifyCode(verifyCode);
		}
		return res;
	}

	@PostMapping(value = "get_reg_time")
	private RegisterResponse getRegTime(@RequestBody RegisterRequest request) {
		return registerService.getRegTime(request.getAccount(), request.getPwd());

	}
	
	//�g�k1:���޿�P�_�g�bcontroller
	@PostMapping(value = "get_reg_time_2")
	private RegisterResponse getRegTime(@RequestBody RegisterRequest request, HttpSession session) {
		
		//���o�w�n�J���b�K
		String account = (String) session.getAttribute("account");
		String pwd = (String) session.getAttribute("pwd");
		
		//����|���n�J
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("�Х��n�J!");
		}
		
		//������J�n�J���ͪ����ҽX
		Integer verifyCode = (Integer) session.getAttribute("verifyCode");
		
		//���n�Jor�ɮĹL�F�N�|�Onull
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("���ҽX���~");
		}
		return registerService.getRegTime(account,pwd);

	}
	
	
	//�g�k2:��Controller²�ơA���޿�^�kservice
	@PostMapping(value = "get_reg_time_3")
	private RegisterResponse getRegTime3(@RequestBody RegisterRequest request, HttpSession session) {
		String account = (String) session.getAttribute("account");
		String pwd = (String) session.getAttribute("pwd");
		Integer verifyCode = (Integer) session.getAttribute("verifyCode");
		
		return registerService.getRegTime3(request, account, pwd, verifyCode);
	}

}
