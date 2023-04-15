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

	//��ӵ�W�P����&�p�����P�ƶq
	@Override
	public Menu getPriceById(String Id) {
		if (!StringUtils.hasText(Id)) { // ==��50��
			return new Menu();
		}
		Optional<Menu> op = orderDAO.findById(Id);
		return op.orElse(new Menu());
		
	}
	
	//�I�\
	@Override
	public void addOrder(Map<String, Integer> orders) {
		for(Entry<String, Integer> orderItem : orders.entrySet()) {
			if(orderItem.getKey() == null) {
				System.out.println("�L�����");
				return;
			}
			if(orderItem.getValue() <= 0) {
				System.out.println("�п�J���T���ƶq");
				return;
			}
		}		
	}
}
