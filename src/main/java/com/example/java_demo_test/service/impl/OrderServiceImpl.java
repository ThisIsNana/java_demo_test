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
		// 新增項目(string 名稱, int價格)進資料庫裡
		// 檢查List不得為null或空
		// 用CollectionUtils
		if (CollectionUtils.isEmpty(menus)) { // ==> if(menus == null || menus.isEmpty()) {
			return new OrderResponse("XX 無新增項目，或新增項目為null或空白 XX");
		}
		for (Menu menuItem : menus) {
//			if (menuItem.getItem() == null || menuItem.getItem().isBlank()) {
			// 用StringUtils
			if (!StringUtils.hasText(menuItem.getItem())) {
				return new OrderResponse("XX 菜名有誤!不得為全空白或null XX");
			}
			if (menuItem.getPrice() <= 0) {
				return new OrderResponse("XX 定價不得為零或負數! XX");
			}
			System.out.printf("菜單已新增：%s，單價：%d元%n", menuItem.getItem(), menuItem.getPrice());
//			orderDAO.save(menuItem);	//盡量避免在for迴圈中一個一個加入的這種寫法，會使用太多次資料庫(增刪修查都是)↓↓
		}
//		return new OrderResponse(menus);
		List<Menu> response = orderDAO.saveAll(menus); // 一次將所有集合加入資料庫可以節省cost!只需使用一次資料庫~
		return new OrderResponse(response, "==addMenu完成==");

	}

	// 點餐
	@Override
	public OrderResponse addOrder(Map<String, Integer> orders) { // "beef:10";"AAA":5;"tea":3
		System.out.println("==============");
		int sumPrice = 0;
		List<String> itemList = new ArrayList<>();
		Map<String, Integer> finalOrderMap = new HashMap();
		for (Entry<String, Integer> orderItem : orders.entrySet()) {
			if (orderItem.getValue() < 0 || orderItem.getValue() == null) {
				return new OrderResponse("XX 餐點數量不得為負數 XX");
			}
			itemList.add(orderItem.getKey()); // 新增進一個只有餐點名稱的List ==> "beef:";"AAA","tea"
		}
		List<Menu> resultOrderList = orderDAO.findAllById(itemList);
		// 找到所有所點的餐點資料 ==> "beef":120(元);"tea":30(元)
		for (Menu menu : resultOrderList) {
			String item = menu.getItem(); // 餐點名稱
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
//			System.out.printf("滿500，打九折！%n");
//		}
//		System.out.printf("合計：%d%n", sumPrice);
		sumPrice = (int) (sumPrice >= 500 ? sumPrice * 0.9 : sumPrice);
		return new OrderResponse(finalOrderMap, sumPrice, "==addOrder完成==");

		// =====================================
		// 用ExistById去寫(老師後來又說不需要所以刪掉了
//		int sumPrice = 0;
//		List<Menu> resultDbMenu = new ArrayList<>();
//		if (orders.isEmpty()) {
//			return new OrderResponse("XX 點餐列表不得為空 XX");
//		}
//		for (Entry<String, Integer> orderItem : orders.entrySet()) {
//			int totalPrice = 0;
//			// 從DAO取出資料 ==> 不推薦在for迴圈裡這麼寫，不建議讓DAO跑太多次
//			Optional<Menu> op = orderDAO.findById(orderItem.getKey());
//			if (!op.isPresent()) {
//				return new OrderResponse("錯誤!輸入的項目皆不存在");
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
//			return new OrderResponse(resultDbMenu, orders, sumPrice, "滿500，打九折。點餐完成。");
//		}
//		return new OrderResponse(resultDbMenu, orders, sumPrice, "點餐完成");

	}

	// 取出資料
	@Override
	public Menu getInfoById(String Id) {
		if (!StringUtils.hasText(Id)) { // ==第50行
			return new Menu();
		}
		Optional<Menu> op = orderDAO.findById(Id);
		return op.orElse(new Menu());
	}

	// API列出所有餐點(無參數)
	public OrderResponse getAllMenus() {
		return new OrderResponse(orderDAO.findAll(), "取得餐點成功!");
	}

	// 作業: 用name查資料回傳
	@Override
	public GetMenuResponse getMenuByName(String name) {
		if (!StringUtils.hasText(name)) {
			return new GetMenuResponse("查無菜單資料");
		}
		Optional<Menu> op = orderDAO.findById(name);
		if (!op.isPresent()) {
			return new GetMenuResponse("餐點不存在");
		}
		return new GetMenuResponse(op.get(), "成功");
	}

	// 1. 只能修改存在的菜單價格
	// 2. 不得為負數
	// 3. 返回修改後的名稱+新價格
	@Override
	public OrderResponse updateMenuPrice(List<Menu> menuList) {
		if (CollectionUtils.isEmpty(menuList)) {
			return new OrderResponse("更改內容不能為null");
		}
		List<String> itemList = new ArrayList<>();
		List<Menu> saveList = new ArrayList<>();
		for (Menu menuListItem : menuList) {
			// 不需要判斷是否menuListItem為null或空，因為即使帶入了也搜尋不到。
			if (menuListItem.getPrice() <= 0) {
				return new OrderResponse("價格有誤");
			}
			itemList.add(menuListItem.getItem());
		}
		// 找到有的項目清單
		List<Menu> resultMenu = orderDAO.findAllById(itemList);
		if (resultMenu.isEmpty()) {
			return new OrderResponse("項目不存在，只能更新既有的菜單~");
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
		return new OrderResponse(saveList, "修改完成");
		// save: 有存在就修改，不存在就新增
	}
}
