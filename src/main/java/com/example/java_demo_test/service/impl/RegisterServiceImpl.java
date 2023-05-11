package com.example.java_demo_test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.repository.RegisterDAO;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDAO regDAO;

	@Override
	public RegisterResponse register(String account, String pwd) {

		// ���b
		if (!StringUtils.hasText(account)) {
			return new RegisterResponse("�b�����o����!");
		}
		if (!StringUtils.hasText(pwd)) {
			return new RegisterResponse("�K�X���o����!");
		}

		Optional<Register> op = regDAO.findById(account);
		if (op.isPresent()) {
			return new RegisterResponse("�b���w�s�b!");
		}

		// �]�w&�W��
		Register newReg = new Register();
		newReg.setAccount(account);
		newReg.setPwd(pwd);

		regDAO.save(newReg);
		return new RegisterResponse("�s�W����");
	}

	@Override
	public RegisterResponse active(String account, String pwd) {

		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("�b���αK�X���i�ť�");
		}

		// �ֹ�b�K
		Register res = regDAO.findByAccountAndPwd(account, pwd);
		if (res == null) {
			return new RegisterResponse("�b���αK�X���~!");
		}

		if (res.isActive() == true) {
			return new RegisterResponse("�w�g���ҹL�F!�Ъ����n�J");
		}

		res.setActive(true);
		regDAO.save(res);
		return new RegisterResponse("�ҥΧ���");
	}

	@Override
	public RegisterResponse login(String account, String pwd) {
		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("�b���αK�X���i�ť�");
		}
		Register res = regDAO.findByAccountAndPwdAndActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("�b��/�K�X���~�A�Υi��|������!");
		}
		return new RegisterResponse("�n�J���\");
	}

	@Override
	public RegisterResponse getRegTime(String account, String pwd) {

		// ����
		Register res = regDAO.findByAccountAndPwdAndActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("�b���B�K�X���~�A�Υi��|������!");
		}

		// ���
		return new RegisterResponse(res.getRegTime(), "�w�q�X���U�ɶ�");
	}

	//�t�@�ؼg�k:��Controller²�ơA���޿�^�kservice
	@Override
	public RegisterResponse getRegTime3(RegisterRequest request, String account, String pwd, Integer verifyCode) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("�Х��n�J!");
		}
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("���ҽX���~");
		}
		Register res = regDAO.findByAccountAndPwdAndActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("�Х��n�J!!");
		}

		// ���
		return new RegisterResponse(res.getRegTime(), "�w�q�X���U�ɶ�");
	}
		

}
