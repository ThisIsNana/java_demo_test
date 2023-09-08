package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.repository.PersonInfoDAO;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

	//勿@Autowired
	private Logger logger = LoggerFactory.getLogger(getClass()); //slf4j
	
	@Autowired
	private PersonInfoDAO personInfoDAO;

	// 1. 新增資訊(重複的不更新)
	@Override
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList) {
		// 第一步：防呆(檢查參數)
		if (CollectionUtils.isEmpty(personInfoList)) {
			return new PersonInfoResponse("新增資料不可為null或空");
		}

		List<String> idList = new ArrayList<>();
		for (PersonInfo personInfo : personInfoList) {
			if (!StringUtils.hasText(personInfo.getId()) || !StringUtils.hasText(personInfo.getName())
					|| personInfo.getAge() < 0 || !StringUtils.hasText(personInfo.getCity())) {
				return new PersonInfoResponse("新增項目有誤！請檢查欄位!");
			}
			idList.add(personInfo.getId());
		}
		// 存在就pass，不存在就新增
		List<PersonInfo> saveList = new ArrayList<>();
		List<PersonInfo> dbList = personInfoDAO.findAllById(idList);
		List<String> dbIdList = new ArrayList<>();
		for (PersonInfo dbPersonInfo : dbList) {
			dbIdList.add(dbPersonInfo.getId());
		}
		if (dbList.size() == personInfoList.size()) {
			return new PersonInfoResponse("無資料新增，id皆重複!");
		}

		if (personInfoDAO.findAllById(idList).size() > 0) {
			for (PersonInfo personInfo : personInfoList) {
				if (!dbIdList.contains(personInfo.getId())) {
					saveList.add(personInfo);
				}
			}
			personInfoDAO.saveAll(saveList);
			return new PersonInfoResponse(saveList, RtnCode.SUCCESSFUL.getMessage());
		}

		logger.info("====PersonInfoServiceImpl====");
		personInfoDAO.saveAll(personInfoList);
		return new PersonInfoResponse(personInfoList, "新增資料完成！");

		// 老師lambda寫法: (請熟悉基本語法再寫) == 第50-58行
//		List<PersonInfo> list = personInfoList.stream()
//				.filter(item -> !dbIdList.contains(item.getId()))
//				.collect(Collectors.toList());		

//		if(personInfoDAO.findAllById(idList).size() > 0) {
//			return new PersonInfoResponse("新增的id已存在!");
//		}
	}


	// 3.用Id取得單一資訊
	@Override
	public GetPersonInfoResponse getPersonInfoById(String id) {
		if (!StringUtils.hasText(id)) {
			return new GetPersonInfoResponse("查詢Id為空");
		}
		Optional<PersonInfo> op = personInfoDAO.findById(id);
		if (!op.isPresent()) {
			return new GetPersonInfoResponse("查無此Id資料");
		}
		return new GetPersonInfoResponse(op.get(), "===查詢成功===");
	}

	@Override
	public GetPersonInfoResponse getAllPersonInfo() {
		List<PersonInfo> allPersonInfo = personInfoDAO.findAll();
		return new GetPersonInfoResponse(allPersonInfo, "===查詢全部資料完成===");
	}

	@Override
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThan(int age) {
		if (age < 0) {
			return new GetPersonInfoResponse("年齡不可為負數");
		}
		List<PersonInfo> personGreaterThan = personInfoDAO.findByAgeGreaterThan(age);
//		List<PersonInfo> resultList = new ArrayList<>();
//		for (PersonInfo personInfo : allPersonInfo) {
//			if (personInfo.getAge() <= age) {
//				continue;
//			}
//			resultList.add(personInfo);
//		}
		if (personGreaterThan.isEmpty()) {
			return new GetPersonInfoResponse("查無符合篩選年齡的資料");
		}
		return new GetPersonInfoResponse(personGreaterThan, "==查詢大於此年齡完成==");
	}

	// 5.小於等於某數，並由小到大排序
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(int age) {
		if (age < 0) {
			return new GetPersonInfoResponse("年齡不可為負數");
		}
		List<PersonInfo> resultList = personInfoDAO.findByAgeLessThanEqualOrderByAgeAsc(age);
		if (resultList.isEmpty()) {
			return new GetPersonInfoResponse("查無符合篩選年齡的資料");
		}
		return new GetPersonInfoResponse(resultList, "==查詢小於等於此年齡完成==");
	}

	// 6.找到前三筆年齡最大的，並由大到小排序
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeBetween(int age1, int age2) {
		if (age1 < 0 || age2 < 0) {
			return new GetPersonInfoResponse("年齡不可為負數");
		}
		List<PersonInfo> resultList = personInfoDAO.findTop3ByAgeBetweenOrderByAgeDesc(age1, age2);
		if (resultList.isEmpty()) {
			return new GetPersonInfoResponse("查無此區間資料");
		}

		return new GetPersonInfoResponse(resultList, "==查詢此年齡區間完成==");
	}

	// 7.取得 city 包含某個特定字的所有個人資訊
	@Override
	public GetPersonInfoResponse getPersonInfoContaining(String str) {
		if (!StringUtils.hasText(str)) {
			return new GetPersonInfoResponse("錯誤!請先輸入關鍵字...");
		}
		List<PersonInfo> list = personInfoDAO.findByCityContaining(str);
		if (list.isEmpty()) {
			return new GetPersonInfoResponse("查無相關資料");
		}
		return new GetPersonInfoResponse(list, "==查詢地區關鍵字完成==");
	}

	// 8. 找出年紀大於輸入條件以及city 包含某個特定字的所有人資訊
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeGreaterAndContaining(int age, String str) {
		if (!StringUtils.hasText(str) || age < 0) {
			return new GetPersonInfoResponse("錯誤! 關鍵字空白或搜尋字串空白!");
		}
		List<PersonInfo> list = personInfoDAO.findByAgeLessThanAndCityContaining(age, str);
		if (list.isEmpty()) {
			return new GetPersonInfoResponse("查無相關資料");
		}
		return new GetPersonInfoResponse(list, "==查詢地區關鍵字完成==");
	}

}
