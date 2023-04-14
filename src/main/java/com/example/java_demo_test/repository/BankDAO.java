package com.example.java_demo_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Bank;
//�ƾڳX�ݼh
@Repository
public interface BankDAO extends JpaRepository<Bank, String> {  
	// <T,ID> T�OEntity�̪�Bank�AID�OPK
	
	public Bank findByAccountAndPwd(String account,String pwd);
	//�`�N�A�o�̷|�۰ʮM�μg�{���R�W���ܼƦW��(�D��Ʈw��column�W��)
}
