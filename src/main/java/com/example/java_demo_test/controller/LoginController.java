//package com.example.java_demo_test.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.java_demo_test.service.ifs.LoginService;
//import com.example.java_demo_test.vo.LoginRequest;
//import com.example.java_demo_test.vo.LoginResponse;
//
//@RestController
//public class LoginController {
//	
//	@Autowired
//	private LoginService loginService;
//	
//	@PostMapping(value = "sign_up")
//	public LoginResponse signUp(@RequestBody LoginRequest request) {
//		return loginService.signUp(request.getLogin());
//	}
//	
//	@PostMapping(value = "veryfy_acc")
//	public LoginResponse verifyAcc(@RequestBody LoginRequest request) {
//		return loginService.verifyAcc(request.getVerifyAccount(),request.getVerifyPwd());
//	}
//	
//	@PostMapping(value = "sign_in")
//	public LoginResponse signIn(@RequestBody LoginRequest request) {
//		return loginService.signIn(request.getSignInAccount(),request.getSignInPwd());
//	}
//	
//	@PostMapping(value = "find_acc_by_city")
//	public LoginResponse findAccByCity(@RequestBody LoginRequest request) {
//		return loginService.findAccByCity(request.getCity());
//	}
//}
