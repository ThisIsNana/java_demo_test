package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddPersonInfoRequest {

	@JsonProperty("add_person_info_list")
	private List<PersonInfo> personInfoList;

	private PersonInfo personinfo;

	public PersonInfo getPersoninfo() {
		return personinfo;
	}

	public void setPersoninfo(PersonInfo personinfo) {
		this.personinfo = personinfo;
	}

	public List<PersonInfo> getPersonInfoList() {
		return personInfoList;
	}

	public void setPersonInfoList(List<PersonInfo> personInfoList) {
		this.personInfoList = personInfoList;
	}
}
