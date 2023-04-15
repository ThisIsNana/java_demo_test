package com.example.java_demo_test.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.repository.OrderDAO;
import com.example.java_demo_test.service.ifs.OrderService;

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

	//對照菜名與價格&計算價格與數量
	@Override
	public Menu getPriceById(String Id) {
		if (!StringUtils.hasText(Id)) { // ==第50行
			return new Menu();
		}
		Optional<Menu> op = orderDAO.findById(Id);
		return op.orElse(new Menu());
		
	}
	
	//點餐
	@Override
	public void addOrder(Map<String, Integer> orders) {
		for(Entry<String, Integer> orderItem : orders.entrySet()) {
			if(orderItem.getKey() == null) {
				System.out.println("無此菜單");
				return;
			}
			if(orderItem.getValue() <= 0) {
				System.out.println("請輸入正確的數量");
				return;
			}
		}		
	}
}
