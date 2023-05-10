package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.vo.LoginResponse;

public interface LoginService {

	public LoginResponse signUp(Login login);
	
	public LoginResponse verifyAcc(String account, String pwd);
	
	public LoginResponse signIn(String account, String pwd);
	
	public LoginResponse findAccByCity(String city);
	

}
