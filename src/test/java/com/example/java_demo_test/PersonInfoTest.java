package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.repository.PersonInfoDAO;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.PersonInfoResponse;

import ch.qos.logback.core.net.SyslogOutputStream;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class PersonInfoTest {


	@Autowired
	private PersonInfoService personInfoService;
	

	@Autowired
	private PersonInfoDAO personDAO;

	@Test
	public void addPersonInfoTest() {
		List<PersonInfo> personList = new ArrayList<>();
		personList.add(new PersonInfo("S5555","������",25,"����"));
		personList.add(new PersonInfo("S6666","������",26,"�̪F"));
		personList.add(new PersonInfo("S7777","���C��",27,"�Ÿq"));
		PersonInfoResponse personInfoResponse = personInfoService.addPersonInfo(personList);
		System.out.println(personInfoResponse.getMessage());
	}
	
	@Test
	public void updateNameByIdTest() {
//		List<PersonInfo> personList = new ArrayList<>();
//		personList.add(new PersonInfo("S0005","������",25,"����"));
		int result = personDAO.updateNameById("S0005","������",25,"����");
		System.out.println(result);
	}
	
//	@Test
//	public void doQueryByAgeTest() {
//		List<PersonInfo> res = personDAO.doQueryByAge(30);
//		System.out.println(res.size());
//	}
//	
//	@Test
//	public void doQueryByAgeLimitTest() {
//		List<PersonInfo> res = personDAO.doQueryByAge(30,3);
//		System.out.println(res.size());
//	}
//
//	@Test
//	public void doQueryByAgeLimitPageTest() {
//		List<PersonInfo> res = personDAO.doQueryByAge(30,3,2);
//		System.out.println(res.size());
//	}
//
//	@Test
//	public void doUpdateAgeByNameTest() {
//		int res = personDAO.doUpdateAgeByName(26,"��02");
//		System.out.println(res);
//	}
	
	@Test	//JPA	�S�� = �������G
	public void searchByNameOrCity_Jpa_Test1() {
		String name = "";
		String city = "";
		List<PersonInfo> result = personDAO.findByNameContainingOrCityContaining(name,city);
		System.out.println(result.size());
	}
	
	@Test	//JPA	�S�� = �L���G
	public void searchByNameOrCity_Jpa_Test2() {
		String name = "";
		String city = "";
		Object result = StringUtils.hasText(name) || StringUtils.hasText(city) ?
				personDAO.findByNameContainingOrCityContaining(name,city) : "not found";
		System.out.println(result.toString());
	}
	
	@Test	//JPQL	�S�� = �������G
	public void searchByNameOrCity_Query_Test1() {
		List<PersonInfo> res = personDAO.searchByNameOrCity("");
		System.out.println(res.size());
	}
	
	@Test	//JPQL	�S�� = �L���G
	public void searchByNameOrCity_Query_Test2() {
		String keyword = "";
		Object result = StringUtils.hasText(keyword) ? personDAO.searchByNameOrCity(keyword) : "Not found";
		System.out.println(result.toString());
	}
	
	@Test	//REGEXP	�S�� = ����
	public void searchByNameOrCity_Regexp_Test1() {
		String keyword = "";
		String result = StringUtils.hasText(keyword) ? keyword : "|"; //�i�H��|(�����)��.(�����null)�B���i�ťո�?
		String newKw = String.join("|", result.split(" "));
		
		List<PersonInfo> resultList = personDAO.searchByNameOrCityRegexp(newKw);
		System.out.println(resultList.size());
	}
	
	@Test	//REGEXP	�S�� = �L���G
	public void searchByNameOrCity_Regexp_Test2() {
		String keyword = "";
		String newKw = String.join("|", keyword.split(" "));
		String result = StringUtils.hasText(newKw) ? keyword : "null";
		
		List<PersonInfo> resultList = personDAO.searchByNameOrCityRegexp(result);
		System.out.println(resultList.size());
	}
}
