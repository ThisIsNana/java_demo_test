package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.AddPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

@RestController
public class PersonInfoController {

	@Autowired
	private PersonInfoService personInfoService;
	
	@PostMapping(value = "add_person_info")
	private PersonInfoResponse addPersonInfo(@RequestBody AddPersonInfoRequest request) {
		return personInfoService.addPersonInfo(request.getPersonInfoList());
	}
	
	@PostMapping(value = "get_person_info_by_id")
	public GetPersonInfoResponse getPersonInfoById(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getPersonInfoById(request.getId());
	}
	
	@PostMapping(value = "get_all_personinfo")
	public GetPersonInfoResponse getAllPersonInfo(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getAllPersonInfo();
	}

	@PostMapping(value = "get_person_info_by_age_greater_than")
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThan(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getPersonInfoByAgeGreaterThan(request.getAge());
	}
	
	@PostMapping(value = "get_top3_by_age_between_by_order")
	public GetPersonInfoResponse getPersonInfoByAgeBetween(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getPersonInfoByAgeBetween(request.getAge1(),request.getAge2());
	}
	
	@PostMapping(value = "find_by_city_containing")
	public GetPersonInfoResponse getPersonInfoContaining(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getPersonInfoContaining(request.getStr());
	}

	@PostMapping(value = "find_by_age_and_containing")
	public GetPersonInfoResponse getPersonInfoByAgeGreaterAndContaining(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getPersonInfoByAgeGreaterAndContaining(request.getAge3(),request.getStr1());
	}
}	
