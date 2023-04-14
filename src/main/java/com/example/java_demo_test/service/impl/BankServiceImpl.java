package com.example.java_demo_test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.repository.BankDAO;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@Service
public class BankServiceImpl implements BankService {

	private String ptn = "\\w{3,8}";

	@Autowired
	private BankDAO bankDAO;

	@Override // �إ߱b��
	public void addInfo(Bank bank) { // ��@���J�ѼƮɡA�����]�n��ۧ�

		// �ˬd�b��
		checkAcc(bank);

		// �ˬd�K�X
		checkPwd(bank);

		// �ˬd�l�B
		checkAmt(bank);

		bankDAO.save(bank);
		// Entity�إߧ���(�s�W���Ʈw��bank��ƪ�)
		// JpaR����k

		System.out.println("==����==");

	}

	// �ˬd�b���G1.����3-8�B2.�b�����঳����ťաB3.�b�����঳�S��Ÿ� =>�ϥΥ��W��F��
	private void checkAcc(Bank bank) {
		// null.�����k���|����!
		if (bank == null) {
			System.out.println("Bank��Ƭ�null");
			return;
		}

		String acc = bank.getAccount();
		String accPtn = "\\w{3,8}";
		if (acc == null || !acc.matches(accPtn)) {
			System.out.println("�b�����~!���ˬd~");
			return;
		}
	}

	// �ˬd�K�X�G1.�Dnull�B2.���঳����ťաB3.����8-16
	private void checkPwd(Bank bank) {
//		String pwdPtn = "\\S{8,16}";
		String pwdPtn = "[\\w~!@#$%^&*]{8,16}";
		String pwd = bank.getPwd();
		if (pwd == null || !pwd.matches(pwdPtn)) {
			System.out.println("�K�X���~!���ˬd~");
			return;
		}
	}

	// �ˬd�l�B�G���o���t��
	private void checkAmt(Bank bank) {
		int amt = bank.getAmount();
		if (amt < 0) {
			System.out.println("���B���o���t��!");
			return;
		}
	}

	// ======================================================
	// ���X���
	@Override
	public Bank getAmountById(String Id) {
		// StringUtils.hasText��String.IsBlank�ܹ��A�����ۤϷN��B�P�[�P�_null�C
		// �T�{����J���T��id
		if (!StringUtils.hasText(Id)) { // ==��50��
			return new Bank();
		}

		// �T�{bank�̦��o��id��ơA�èϥ�Option���^
		Optional<Bank> op = bankDAO.findById(Id);
//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		//�u���S���D�NGET���--op.get��k�u�ϥΦbid(PK)
//		return op.get();

		// �������Y²�g������========

		// 90~95 �i�H�ΤU�����y�l���N
		// ���F��N�^��value(== op.get)�A�S���F��N�^��orElse()�̪��F��
//		Bank bank = op.orElse(new Bank());
//		return bank;

		// �����������Y²�g��������=====

		return op.orElse(new Bank());

	}

	@Override // �s��
	public BankResponse deposit(Bank bank) {
		// �T�{��J���T����T
		if (bank == null || !StringUtils.hasText(bank.getAccount()) || !StringUtils.hasText(bank.getPwd())
				|| bank.getAmount() <= 0) {
			return new BankResponse(new Bank(), "�b���B�K�X�Ϊ��B�榡���~�C");
		}
//		//�T�{��J����T"�s�b"
//		Optional<Bank> op = bankDAO.findById(bank.getAccount());
//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		Bank resultBank = op.get();
//		//�T�{�K�X
//		if(!resultBank.getPwd().equals(bank.getPwd())) {
//			return new Bank();
//		}

		// ====�����ϥΦbbankDAO�̦۳Уx��k�B��Bank���^�C�P�ɽT�{�b���P�K�X
		Bank resultBank = bankDAO.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if (resultBank == null) {
			return new BankResponse(new Bank(), "��Ƥ��s�b!�b���αK�X���~!");
		}

		// �s�ڤ���
		int newAmount = resultBank.getAmount() + bank.getAmount();

		resultBank.setAmount(newAmount);
		return new BankResponse(bankDAO.save(resultBank),"�s�ڧ���!");
	}

	@Override // ����
	public BankResponse withdraw(Bank bank) {
		// �T�{��J"���T"����T
		if (bank == null 
				|| !StringUtils.hasText(bank.getAccount()) 
				|| !StringUtils.hasText(bank.getPwd())
				|| bank.getAmount() <= 0) {
			return new BankResponse(new Bank(), "�b���B�K�X�Ϊ��B�榡���~�C");
		}

		Bank resultBank = bankDAO.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if (resultBank == null) {
			return new BankResponse(new Bank(), "��Ƥ��s�b!�b���αK�X���~!");
		}

		// ���ڤ���
		int newAmount = resultBank.getAmount() - bank.getAmount();
		if (newAmount < 0) {
			return new BankResponse(new Bank(), "�l�B����!");
		}
		resultBank.setAmount(newAmount);
		return new BankResponse(bankDAO.save(resultBank),"���ڧ���!");

	}

}
