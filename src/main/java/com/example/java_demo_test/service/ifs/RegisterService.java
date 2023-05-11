package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

public interface RegisterService {

	public RegisterResponse register(String account, String pwd);
	
	public RegisterResponse active(String account, String pwd);

	public RegisterResponse login(String account, String pwd);
	
	public RegisterResponse getRegTime(String account, String pwd);

	public RegisterResponse getRegTime3(RegisterRequest request, String account, String pwd, Integer verifyCode);
}
