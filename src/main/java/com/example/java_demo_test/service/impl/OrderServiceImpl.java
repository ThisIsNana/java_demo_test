package com.example.java_demo_test.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.repository.OrderDAO;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.BankResponse;
import com.example.java_demo_test.vo.OrderResponse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public void addMenu(List<Menu> menus) {
		// 新增項目(string 名稱, int價格)進資料庫裡
		for (Menu menuItem : menus) {
			if (menuItem.getItem() == null) {
				System.out.println("菜名有誤!");
				return;
			}
			if (menuItem.getPrice() <= 0) {
				System.out.println("定價不得為零或負數");
				return;
			}
			orderDAO.save(menuItem);
		}
		System.out.println("==新增菜單完成==");

	}

	// 取出資料
	@Override
	public Menu getPriceById(String Id) {
		if (!StringUtils.hasText(Id)) { // ==第50行
			return new Menu();
		}
		Optional<Menu> op = orderDAO.findById(Id);
		return op.orElse(new Menu());
		
	}
	
	// 點餐
	@Override
	public OrderResponse addOrder(Map<String, Integer> orders) {
		System.out.println("==============");
		int sumPrice = 0;
		for (Entry<String, Integer> orderItem : orders.entrySet()) {
			int totalPrice = 0;
			// 先確認點餐內容
			if (orderItem.getKey().isBlank() || orderItem.getKey() == null) {
				return new OrderResponse(new Menu(), "無此菜單");
			}
			if (orderItem.getValue() <= 0 || orderItem.getValue() == null) {
				return new OrderResponse(new Menu(), "餐點數量不得為負數");
			}
			// 從DAO取出價格資料
			int resultPrice = getPriceById(orderItem.getKey()).getPrice();
			totalPrice = orderItem.getValue() * resultPrice;
			sumPrice += orderItem.getValue() * resultPrice;
			System.out.printf("餐點：%s，價格%d，數量：%d，小計:%d%n",orderItem.getKey(),resultPrice,orderItem.getValue(),totalPrice);
		}
		System.out.println("==============");
		if(sumPrice >= 500) {
			sumPrice *= 0.9;
			System.out.printf("滿500，打九折！%n");
		}
		System.out.printf("合計：%d%n",sumPrice);
		return new OrderResponse("==點餐完成==");
	}
}
