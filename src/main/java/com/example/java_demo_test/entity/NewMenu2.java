package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "new_menu2")
@IdClass(value = NewMenu2Id.class)
public class NewMenu2 {
	
		@Id
		@Column(name = "category")
		private String category;

		@Id
		@Column(name = "cookway")
		private String cookWay;

		@Column(name = "price")
		private int price;

		public NewMenu2() {
			super();
		}

		public NewMenu2(String category, String cookWay, int price) {
			super();
			this.category = category;
			this.cookWay = cookWay;
			this.price = price;
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
		
}
