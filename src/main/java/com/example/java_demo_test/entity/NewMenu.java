package com.example.java_demo_test.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "new_menu")
public class NewMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 當ID為無意義的流水號時可使用。(僅限int)
	@Column(name = "seg")
	private int seg;

	@Column(name = "category")
	private String category;

	@Column(name = "cookway")
	private String cookWay;

	@Column(name = "price")
	private int price;

	@Column(name = "uuid")
	@Type(type = "uuid-char") // 讓程式知道這是UUID
	private UUID uuid = UUID.randomUUID(); // 注意型態為UUID皆大寫，用Random給預設值

	public NewMenu() {
		super();
	}

	public NewMenu(String category, String cookWay, int price) {
		super();
		this.category = category;
		this.cookWay = cookWay;
		this.price = price;
	}

	public NewMenu(String category, String cookWay, int price, UUID uuid) {
		super();
		this.category = category;
		this.cookWay = cookWay;
		this.price = price;
		this.uuid = uuid;
	}

	public NewMenu(int seg, String category, String cookWay, int price) {
		super();
		this.seg = seg;
		this.category = category;
		this.cookWay = cookWay;
		this.price = price;
	}

	public int getSeg() {
		return seg;
	}

	public void setSeg(int seg) {
		this.seg = seg;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCookWay() {
		return cookWay;
	}

	public void setCookWay(String cookWay) {
		this.cookWay = cookWay;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
