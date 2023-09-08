package com.example.java_demo_test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiTest {

	@SuppressWarnings("unchecked")
	@Test
	public void getApiTest() throws JsonMappingException, JsonProcessingException {
		String targetUrl = "https://opendata.hccg.gov.tw/API/v3/Rest/OpenData/704FC0B2EE7500E4?take=5&skip=10";
		RestTemplate restTemplate = new RestTemplate();
//		String resStr = restTemplate.getForObject(targetUrl, String.class);
		ResponseEntity<String> resString = restTemplate.getForEntity(targetUrl, String.class);

		// 列印取出的資料
		System.out.println(resString.getStatusCode());
		System.out.println(resString.getStatusCodeValue());
		String responseString = resString.getBody();
		System.out.println(responseString);

		System.out.println("=========================");

		ObjectMapper mapper = new ObjectMapper();
		// readValue前面是目標字串，後方是要轉過去的格式 => 紅蚯蚓 -> 生成throw
		List<Map<String, Object>> resList = mapper.readValue(responseString, List.class); 

		for (Map<String, Object> item : resList) {
			for (Entry<String, Object> map : item.entrySet()) {
				System.out.printf("key: %s, value: %s %n", map.getKey(), (String) map.getValue());
//				System.out.println("key:" + map.getKey());
//				System.out.println("　　　　　　value:" + (String)map.getValue());
			}
		}

	}

	@Test
	public void postApiTest() {
		String targetUrl = "http://172.20.10.5:8080/api/register";
		// 欄位 , 內容
		Map<String, String> bodyMap = new HashMap<>();
		bodyMap.put("account", "nana9438");
		bodyMap.put("pwd", "AA111");

		ObjectMapper mapper = new ObjectMapper();
		try {
			String reqBodyStr = mapper.writeValueAsString(bodyMap);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			
			//告知格式：今天傳輸過去的東西是用JSON來讀取↓↓
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			//把Body跟Headers合在一起
			HttpEntity<String> requestBody = new HttpEntity<String>(reqBodyStr,headers);
			ResponseEntity<String> response = restTemplate.postForEntity(targetUrl, requestBody, String.class);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
