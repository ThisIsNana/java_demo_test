package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;
import com.example.java_demo_test.vo.GetPersonInfoRequest;

public interface PersonInfoService {
	
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList); 
	
	public GetPersonInfoResponse getPersonInfoById(String id);
	
	public GetPersonInfoResponse getAllPersonInfo();
	
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThan(int age);
	
	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(int age);
	
	public GetPersonInfoResponse getPersonInfoByAgeBetween(int age1,int age2);
	
	public GetPersonInfoResponse getPersonInfoContaining(String str);
	
	public GetPersonInfoResponse getPersonInfoByAgeGreaterAndContaining(int age,String str);
	
}

