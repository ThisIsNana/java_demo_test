package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.repository.OrderDAO;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public OrderResponse addMenu(List<Menu> menus) {
		// �s�W����(string �W��, int����)�i��Ʈw��
		// �ˬdList���o��null�Ϊ�
		// ��CollectionUtils
		if (CollectionUtils.isEmpty(menus)) { // ==> if(menus == null || menus.isEmpty()) {
			return new OrderResponse("XX �L�s�W���ءA�ηs�W���ج�null�Ϊť� XX");
		}
		for (Menu menuItem : menus) {
//			if (menuItem.getItem() == null || menuItem.getItem().isBlank()) {
			// ��StringUtils
			if (!StringUtils.hasText(menuItem.getItem())) {
				return new OrderResponse("XX ��W���~!���o�����ťթ�null XX");
			}
			if (menuItem.getPrice() <= 0) {
				return new OrderResponse("XX �w�����o���s�έt��! XX");
			}
			System.out.printf("���w�s�W�G%s�A����G%d��%n", menuItem.getItem(), menuItem.getPrice());
//			orderDAO.save(menuItem);	//�ɶq�קK�bfor�j�餤�@�Ӥ@�ӥ[�J���o�ؼg�k�A�|�ϥΤӦh����Ʈw(�W�R�׬d���O)����
		}
//		return new OrderResponse(menus);
		List<Menu> response = orderDAO.saveAll(menus); // �@���N�Ҧ����X�[�J��Ʈw�i�H�`��cost!�u�ݨϥΤ@����Ʈw~
		return new OrderResponse(response, "==addMenu����==");

	}

	// �I�\
	@Override
	public OrderResponse addOrder(Map<String, Integer> orders) { // "beef:10";"AAA":5;"tea":3
		System.out.println("==============");
		int sumPrice = 0;
		List<String> itemList = new ArrayList<>();
		Map<String, Integer> finalOrderMap = new HashMap();
		for (Entry<String, Integer> orderItem : orders.entrySet()) {
			if (orderItem.getValue() < 0 || orderItem.getValue() == null) {
				return new OrderResponse("XX �\�I�ƶq���o���t�� XX");
			}
			itemList.add(orderItem.getKey()); // �s�W�i�@�ӥu���\�I�W�٪�List ==> "beef:";"AAA","tea"
		}
		List<Menu> resultOrderList = orderDAO.findAllById(itemList);
		// ���Ҧ����I���\�I��� ==> "beef":120(��);"tea":30(��)
		for (Menu menu : resultOrderList) {
			String item = menu.getItem(); // �\�I�W��
			int price = menu.getPrice();
			for (Entry<String, Integer> map : orders.entrySet()) {
				String key = map.getKey();
				int value = map.getValue();
				if (item.equals(key)) {
					int singleTotalPrice = price * value;
					sumPrice += singleTotalPrice;
					finalOrderMap.put(key, value);
				}
			}
		}
//		if (sumPrice >= 500) {
//			sumPrice *= 0.9;
//			System.out.printf("��500�A���E��I%n");
//		}
//		System.out.printf("�X�p�G%d%n", sumPrice);
		sumPrice = (int) (sumPrice >= 500 ? sumPrice * 0.9 : sumPrice);
		return new OrderResponse(finalOrderMap, sumPrice, "==addOrder����==");

		// =====================================
		// ��ExistById�h�g(�Ѯv��ӤS�����ݭn�ҥH�R���F
//		int sumPrice = 0;
//		List<Menu> resultDbMenu = new ArrayList<>();
//		if (orders.isEmpty()) {
//			return new OrderResponse("XX �I�\�C���o���� XX");
//		}
//		for (Entry<String, Integer> orderItem : orders.entrySet()) {
//			int totalPrice = 0;
//			// �qDAO���X��� ==> �����˦bfor�j��̳o��g�A����ĳ��DAO�]�Ӧh��
//			Optional<Menu> op = orderDAO.findById(orderItem.getKey());
//			if (!op.isPresent()) {
//				return new OrderResponse("���~!��J�����جҤ��s�b");
//			}
//
//			int quantity = orderItem.getValue();
//			int price = op.get().getPrice();
//
//			totalPrice = quantity * price;
//			sumPrice += totalPrice;
//			resultDbMenu.add(op.get());
//		}
//		if (sumPrice >= 500) {
//			sumPrice *= 0.9;
//			return new OrderResponse(resultDbMenu, orders, sumPrice, "��500�A���E��C�I�\�����C");
//		}
//		return new OrderResponse(resultDbMenu, orders, sumPrice, "�I�\����");

	}

	// ���X���
	@Override
	public Menu getInfoById(String Id) {
		if (!StringUtils.hasText(Id)) { // ==��50��
			return new Menu();
		}
		Optional<Menu> op = orderDAO.findById(Id);
		return op.orElse(new Menu());
	}

	// API�C�X�Ҧ��\�I(�L�Ѽ�)
	public OrderResponse getAllMenus() {
		return new OrderResponse(orderDAO.findAll(), "���o�\�I���\!");
	}

	// �@�~: ��name�d��Ʀ^��
	@Override
	public GetMenuResponse getMenuByName(String name) {
		if (!StringUtils.hasText(name)) {
			return new GetMenuResponse("�d�L�����");
		}
		Optional<Menu> op = orderDAO.findById(name);
		if (!op.isPresent()) {
			return new GetMenuResponse("�\�I���s�b");
		}
		return new GetMenuResponse(op.get(), "���\");
	}

	// 1. �u��ק�s�b��������
	// 2. ���o���t��
	// 3. ��^�ק�᪺�W��+�s����
	@Override
	public OrderResponse updateMenuPrice(List<Menu> menuList) {
		if (CollectionUtils.isEmpty(menuList)) {
			return new OrderResponse("��鷺�e���ରnull");
		}
		List<String> itemList = new ArrayList<>();
		List<Menu> saveList = new ArrayList<>();
		for (Menu menuListItem : menuList) {
			// ���ݭn�P�_�O�_menuListItem��null�ΪšA�]���Y�ϱa�J�F�]�j�M����C
			if (menuListItem.getPrice() <= 0) {
				return new OrderResponse("���榳�~");
			}
			itemList.add(menuListItem.getItem());
		}
		// ��즳�����زM��
		List<Menu> resultMenu = orderDAO.findAllById(itemList);
		if (resultMenu.isEmpty()) {
			return new OrderResponse("���ؤ��s�b�A�u���s�J�������~");
		}
		for (Menu result : resultMenu) {
			for (Menu menuListItem : menuList) {
				if (result.getItem().equals(menuListItem.getItem())) {
//					result.setPrice(menuListItem.getPrice());
//					saveList.add(result);
					saveList.add(menuListItem);
				}
			}
		}

		orderDAO.saveAll(saveList);
		return new OrderResponse(saveList, "�ק粒��");
		// save: ���s�b�N�ק�A���s�b�N�s�W
	}
}
