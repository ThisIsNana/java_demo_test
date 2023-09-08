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

	//��@Autowired
	private Logger logger = LoggerFactory.getLogger(getClass()); //slf4j
	
	@Autowired
	private PersonInfoDAO personInfoDAO;

	// 1. �s�W��T(���ƪ�����s)
	@Override
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList) {
		// �Ĥ@�B�G���b(�ˬd�Ѽ�)
		if (CollectionUtils.isEmpty(personInfoList)) {
			return new PersonInfoResponse("�s�W��Ƥ��i��null�Ϊ�");
		}

		List<String> idList = new ArrayList<>();
		for (PersonInfo personInfo : personInfoList) {
			if (!StringUtils.hasText(personInfo.getId()) || !StringUtils.hasText(personInfo.getName())
					|| personInfo.getAge() < 0 || !StringUtils.hasText(personInfo.getCity())) {
				return new PersonInfoResponse("�s�W���ئ��~�I���ˬd���!");
			}
			idList.add(personInfo.getId());
		}
		// �s�b�Npass�A���s�b�N�s�W
		List<PersonInfo> saveList = new ArrayList<>();
		List<PersonInfo> dbList = personInfoDAO.findAllById(idList);
		List<String> dbIdList = new ArrayList<>();
		for (PersonInfo dbPersonInfo : dbList) {
			dbIdList.add(dbPersonInfo.getId());
		}
		if (dbList.size() == personInfoList.size()) {
			return new PersonInfoResponse("�L��Ʒs�W�Aid�ҭ���!");
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
		return new PersonInfoResponse(personInfoList, "�s�W��Ƨ����I");

		// �Ѯvlambda�g�k: (�м��x�򥻻y�k�A�g) == ��50-58��
//		List<PersonInfo> list = personInfoList.stream()
//				.filter(item -> !dbIdList.contains(item.getId()))
//				.collect(Collectors.toList());		

//		if(personInfoDAO.findAllById(idList).size() > 0) {
//			return new PersonInfoResponse("�s�W��id�w�s�b!");
//		}
	}


	// 3.��Id���o��@��T
	@Override
	public GetPersonInfoResponse getPersonInfoById(String id) {
		if (!StringUtils.hasText(id)) {
			return new GetPersonInfoResponse("�d��Id����");
		}
		Optional<PersonInfo> op = personInfoDAO.findById(id);
		if (!op.isPresent()) {
			return new GetPersonInfoResponse("�d�L��Id���");
		}
		return new GetPersonInfoResponse(op.get(), "===�d�ߦ��\===");
	}

	@Override
	public GetPersonInfoResponse getAllPersonInfo() {
		List<PersonInfo> allPersonInfo = personInfoDAO.findAll();
		return new GetPersonInfoResponse(allPersonInfo, "===�d�ߥ�����Ƨ���===");
	}

	@Override
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThan(int age) {
		if (age < 0) {
			return new GetPersonInfoResponse("�~�֤��i���t��");
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
			return new GetPersonInfoResponse("�d�L�ŦX�z��~�֪����");
		}
		return new GetPersonInfoResponse(personGreaterThan, "==�d�ߤj�󦹦~�֧���==");
	}

	// 5.�p�󵥩�Y�ơA�åѤp��j�Ƨ�
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(int age) {
		if (age < 0) {
			return new GetPersonInfoResponse("�~�֤��i���t��");
		}
		List<PersonInfo> resultList = personInfoDAO.findByAgeLessThanEqualOrderByAgeAsc(age);
		if (resultList.isEmpty()) {
			return new GetPersonInfoResponse("�d�L�ŦX�z��~�֪����");
		}
		return new GetPersonInfoResponse(resultList, "==�d�ߤp�󵥩󦹦~�֧���==");
	}

	// 6.���e�T���~�ֳ̤j���A�åѤj��p�Ƨ�
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeBetween(int age1, int age2) {
		if (age1 < 0 || age2 < 0) {
			return new GetPersonInfoResponse("�~�֤��i���t��");
		}
		List<PersonInfo> resultList = personInfoDAO.findTop3ByAgeBetweenOrderByAgeDesc(age1, age2);
		if (resultList.isEmpty()) {
			return new GetPersonInfoResponse("�d�L���϶����");
		}

		return new GetPersonInfoResponse(resultList, "==�d�ߦ��~�ְ϶�����==");
	}

	// 7.���o city �]�t�Y�ӯS�w�r���Ҧ��ӤH��T
	@Override
	public GetPersonInfoResponse getPersonInfoContaining(String str) {
		if (!StringUtils.hasText(str)) {
			return new GetPersonInfoResponse("���~!�Х���J����r...");
		}
		List<PersonInfo> list = personInfoDAO.findByCityContaining(str);
		if (list.isEmpty()) {
			return new GetPersonInfoResponse("�d�L�������");
		}
		return new GetPersonInfoResponse(list, "==�d�ߦa������r����==");
	}

	// 8. ��X�~���j���J����H��city �]�t�Y�ӯS�w�r���Ҧ��H��T
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeGreaterAndContaining(int age, String str) {
		if (!StringUtils.hasText(str) || age < 0) {
			return new GetPersonInfoResponse("���~! ����r�ťթηj�M�r��ť�!");
		}
		List<PersonInfo> list = personInfoDAO.findByAgeLessThanAndCityContaining(age, str);
		if (list.isEmpty()) {
			return new GetPersonInfoResponse("�d�L�������");
		}
		return new GetPersonInfoResponse(list, "==�d�ߦa������r����==");
	}

}
