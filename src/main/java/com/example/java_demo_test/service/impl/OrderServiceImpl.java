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
		// �s�W����(string �W��, int����)�i��Ʈw��
		for (Menu menuItem : menus) {
			if (menuItem.getItem() == null) {
				System.out.println("��W���~!");
				return;
			}
			if (menuItem.getPrice() <= 0) {
				System.out.println("�w�����o���s�έt��");
				return;
			}
			orderDAO.save(menuItem);
		}
		System.out.println("==�s�W��槹��==");

	}

	// ���X���
	@Override
	public Menu getPriceById(String Id) {
		if (!StringUtils.hasText(Id)) { // ==��50��
			return new Menu();
		}
		Optional<Menu> op = orderDAO.findById(Id);
		return op.orElse(new Menu());
		
	}
	
	// �I�\
	@Override
	public OrderResponse addOrder(Map<String, Integer> orders) {
		System.out.println("==============");
		int sumPrice = 0;
		for (Entry<String, Integer> orderItem : orders.entrySet()) {
			int totalPrice = 0;
			// ���T�{�I�\���e
			if (orderItem.getKey().isBlank() || orderItem.getKey() == null) {
				return new OrderResponse(new Menu(), "�L�����");
			}
			if (orderItem.getValue() <= 0 || orderItem.getValue() == null) {
				return new OrderResponse(new Menu(), "�\�I�ƶq���o���t��");
			}
			// �qDAO���X������
			int resultPrice = getPriceById(orderItem.getKey()).getPrice();
			totalPrice = orderItem.getValue() * resultPrice;
			sumPrice += orderItem.getValue() * resultPrice;
			System.out.printf("�\�I�G%s�A����%d�A�ƶq�G%d�A�p�p:%d%n",orderItem.getKey(),resultPrice,orderItem.getValue(),totalPrice);
		}
		System.out.println("==============");
		if(sumPrice >= 500) {
			sumPrice *= 0.9;
			System.out.printf("��500�A���E��I%n");
		}
		System.out.printf("�X�p�G%d%n",sumPrice);
		return new OrderResponse("==�I�\����==");
	}
}
