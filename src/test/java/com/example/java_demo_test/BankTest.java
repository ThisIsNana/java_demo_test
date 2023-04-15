package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.repository.BankDAO;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class BankTest {

	@Autowired
	private BankDAO bankDAO;
	
	@Autowired
	private BankService bankService ;
	
//	@Test
//	public void addBankInfo() {
//		Bank bank = new Bank("A001","pwd0000",1000); 
//		//Entity�إߧ���(�s�W���Ʈw��bank��ƪ�)
//		bankDAO.save(bank); //JpaR����k
//	}
	
	@Test
	public void addInfoTest() {
		Bank bank = new Bank("acc999","pwd99999@",2000);
		bankService.addInfo(bank);
	}
	
	@Test
	public void getAmountByIdTest() {
		Bank bank = bankService.getAmountById("acc001");
		System.out.printf("�b��G%s�A�l�B�G%d%n",bank.getAccount(),bank.getAmount());
	}
	
	@Test
	public void depositTest() {
		//���Ыش��ո��
		Bank oldBank = bankDAO.save(new Bank("acc999","pwd99999@", 2000));
		//�s��--�T�{�b�K�ۦP
		Bank newBank = new Bank("acc999","pwd99999@", 3000);
		BankResponse response = bankService.deposit(newBank);
		//���ժ��B�s�J���O�_���T.isTrue(����,false�ɪ����~�T��)
		Bank resultBank = response.getBank();		
		Assert.isTrue(resultBank.getAmount() == oldBank.getAmount() + newBank.getAmount(), "���B���~");
		//�R�����ո��
		bankDAO.delete(resultBank);
		
	}
	
	@Test
	public void withdrawTest() {
		//���Ыش��ո��
		Bank oldBank = bankDAO.save(new Bank("acc999","pwd99999@", 2000));
		//����--�T�{�b�K�ۦP
		Bank newBank = new Bank("acc999","pwd99999", 1500);
		BankResponse response = bankService.withdraw(newBank);
		//���ժ��B�s�J���O�_���T.isTrue(�w�����G,false�ɪ����~�T��)
		Bank resultBank = response.getBank();
		Assert.isTrue(resultBank.getAmount() == oldBank.getAmount() - newBank.getAmount(), "���~~");
		Assert.isTrue(response.getMessage().equals("���ڧ���!"), "���ڥ���");
		System.out.printf("�b��G%s�A�l�B�G%d%n",resultBank.getAccount(),resultBank.getAmount());
		response.getMessage();
		System.out.println();
		//�R�����ո��
		bankDAO.delete(resultBank);
		
		//orderR�Glistmenu �T�� ��l���� �u�f�����
	}
	
}
