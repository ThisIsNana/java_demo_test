package com.example.java_demo_test.constants;

public enum RtnCode {
// �s��`�ƤΦ^��constants = �`�ơARtn = return
	SUCCESSFUL("200", "����!"),
	CANNOT_EMTPY("400","�b���αK�X���i�ť�"), //�e���W�r�i�ۤv�w�q
	DATA_ERROR("400","�b���B�K�X���~�ή榡���~"),
	UNAUTHORIZED("401","�b��������"),
	ALREADY_AUTHORIZED("401","�b���w���ҡA�Ъ����n�J"),
	NOT_FOUND("404","�d�L���"),
	ALREADY_PRESENT("409","���Ʒs�W");
	
	private String code;
	private String message;

	private RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
