package com.example.java_demo_test.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RegisterResponse {

	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("verify_code")
	private int verifyCode;

	@JsonProperty("reg_time")
	private LocalDateTime regTime;

	private String message;

	// ======================================

	public RegisterResponse() {
		super();
	}

	public RegisterResponse(String message) {
		super();
		this.message = message;
	}

	public RegisterResponse(LocalDateTime regTime, String message) {
		super();
		this.regTime = regTime;
		this.message = message;
	}

	public RegisterResponse(String sessionId, int verifyCode, String message) {
		super();
		this.sessionId = sessionId;
		this.verifyCode = verifyCode;
		this.message = message;
	}
	
	// ======================================

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}

}
