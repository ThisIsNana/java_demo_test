package com.example.java_demo_test.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.PersonInfo;

public class PersonInfoDaoImpl extends BaseDAO {
	
	public List<PersonInfo> doQueryByAge(int age){
		StringBuffer sb = new StringBuffer(); //有緩衝暫存空間的SB
		sb.append("select p from PersonInfo p where p.age >= :inputAge"); 
		//append:把字串串起來的高級用法。(sb限定)
		//使用Entity的名稱(小駝峰)(因為BaseDAO寫的是CreateQuery方法)而不是person_info
		Map<String, Object> params = new HashMap<>();
		params.put("inputAge", age); //前面放:後面的age，後面的age放第11行的int age
		return doQuery(sb.toString(), params, PersonInfo.class);		
	}
	
	public List<PersonInfo> doQueryByAge(int age, int limitSize){
		StringBuffer sb = new StringBuffer();
		sb.append("select p from PersonInfo p where p.age >= :inputAge"); 
		Map<String, Object> params = new HashMap<>();
		params.put("inputAge", age);
		return doQuery(sb.toString(), params, PersonInfo.class, limitSize);		
	}
	
	public List<PersonInfo> doQueryByAge(int age, int limitSize, int startPosition){
		StringBuffer sb = new StringBuffer();
		sb.append("select p from PersonInfo p where p.age >= :inputAge"); 
		Map<String, Object> params = new HashMap<>();
		params.put("inputAge", age);
		return doQuery(sb.toString(), params, PersonInfo.class, limitSize, startPosition);		
	}
	
	public int doUpdateAgeByName(int age, String name) {
		StringBuffer sb = new StringBuffer();
		sb.append("update PersonInfo set age = :age where name = :name ");
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		params.put("age", age);
		return doUpdate(sb.toString(), params);
	}
	

}
