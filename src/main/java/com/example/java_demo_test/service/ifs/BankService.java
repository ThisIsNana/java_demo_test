package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.vo.BankResponse;

public interface BankService {

	//�W�[���
	public void addInfo(Bank bank);
	
	//���X��� bank = �^�ǫ��A ; Id = ���U@Id�����
	public Bank getAmountById(String Id);
	
	//�s��
	public BankResponse deposit(Bank bank);
	
	//����
	public BankResponse withdraw(Bank bank);

}
