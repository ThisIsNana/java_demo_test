package com.example.java_demo_test.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.repository.LoginDAO;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginResponse;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;

	@Override
	public LoginResponse signUp(Login login) {
		// ���b
//		checkAcc(login);
		if (login == null) {
			return new LoginResponse(RtnCode.CANNOT_EMTPY.getMessage());
		}
		// �ˬd����
		Optional<Login> op = loginDAO.findById(login.getAccount());
		if (op.isPresent()) {
			return new LoginResponse(RtnCode.ALREADY_PRESENT.getMessage());
		}
		// �ˬd�榡
		String acc = login.getAccount();
		String accPtn = "\\w{3,8}";
		if (acc == null || !acc.matches(accPtn)) {
			return new LoginResponse(RtnCode.CANNOT_EMTPY.getMessage());
		}
//		checkPwd(login);
		String pwdPtn = "^(?=.*[~@$!%*?&])[\\w]{8,12}";
		String pwd = login.getPwd();
		if (pwd == null) {
			return new LoginResponse(RtnCode.CANNOT_EMTPY.getMessage());
		}
		if (!pwd.matches(pwdPtn)) {
			return new LoginResponse(RtnCode.DATA_ERROR.getMessage());
		}
//		checkName(login);
		String name = login.getName();
		if (!StringUtils.hasText(name)) {
			return new LoginResponse(RtnCode.CANNOT_EMTPY.getMessage());
		}
		LocalDateTime now = LocalDateTime.now();
		login.setRegisterTime(now);
		loginDAO.save(login);
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public LoginResponse verifyAcc(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new LoginResponse(RtnCode.CANNOT_EMTPY.getMessage());
		}
		//
		Login logAccPwd = loginDAO.findByAccountAndPwd(account, pwd);
		if (logAccPwd == null) {
			return new LoginResponse(RtnCode.DATA_ERROR.getMessage());
		}
		if (logAccPwd.isActive() == true) {
			return new LoginResponse(RtnCode.ALREADY_AUTHORIZED.getMessage());
		}
		logAccPwd.setActive(true);
		loginDAO.save(logAccPwd);
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
		
	}
	
	

	@Override
	public LoginResponse signIn(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new LoginResponse(RtnCode.CANNOT_EMTPY.getMessage());
		}
		Login logAccPwd = loginDAO.findByAccountAndPwd(account, pwd);
		if (logAccPwd == null) {
			return new LoginResponse(RtnCode.DATA_ERROR.getMessage());
		}
		if (logAccPwd.isActive() == false) {
			return new LoginResponse(RtnCode.UNAUTHORIZED.getMessage());
		}
		return new LoginResponse(logAccPwd.getName(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public LoginResponse findAccByCity(String city) {
		if(!StringUtils.hasText(city)) {
			return new LoginResponse(RtnCode.CANNOT_EMTPY.getMessage());
		}
		List<Login> resultCity = loginDAO.findAllByCityContainingOrderByAgeAsc(city);
		if(resultCity == null) {
			return new LoginResponse(RtnCode.NOT_FOUND.getMessage());
		}
		List<Login> resList = new ArrayList<>();
		for(Login result : resultCity) {
			Login login = new Login(result.getAccount(),null,result.getName(),result.getAge(),
					result.getCity(),null,result.isActive());
			resList.add(login);
		}
		return new LoginResponse(resList,RtnCode.SUCCESSFUL.getMessage());
	}
	
	
	// =========�D�\��=================================
	// �ˬd�b��
//	private void checkAcc(Login login) {
//		if (login == null) {
//			System.out.println(new LoginResponse("login��Ƥ��o��null"));
//			return;
//		}
//		// �ˬd����
//		Optional<Login> op = loginDAO.findById(login.getAccount());
//		if (op.isPresent()) {
//			System.out.println(new LoginResponse("���b���w�s�b!"));
//			return;
//		}
//		// �ˬd�榡
//		String acc = login.getAccount();
//		String accPtn = "\\w{3,8}";
//		if (acc == null || !acc.matches(accPtn)) {
//			System.out.println(new LoginResponse("�b���ݤ���3~8�r�����A���i���ť�!"));
//			return;
//		}
//	}

	// �ˬd�K�X
//	private void checkPwd(Login login) {
//		String pwdPtn = "[\\w~!@#$%^&*]{8,12}";
//		String pwd = login.getPwd();
//		if (pwd == null || !pwd.matches(pwdPtn)) {
//			System.out.println("�K�X���~!�ŪťաB�ݤ���8-12�r�����A�i�H�ϥίS��Ÿ�");
//			return;
//		}
//	}

	// �ˬd�m�W
//	private void checkName(Login login) {
//		String name = login.getName();
//		if (!StringUtils.hasText(name)) {
//			System.out.println(new LoginResponse("�m�W���o����!"));
//			return;
//		}
//	}

}
