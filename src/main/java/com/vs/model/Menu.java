package com.vs.model;

import java.util.Collection;

public class Menu {
	
	private String restorantName;
	private Collection<Dish> dishes;

	public String getRestorantName() {
		return restorantName;
	}

	public void setRestorantName(String restorantName) {
		this.restorantName = restorantName;
	}

	public Collection<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Collection<Dish> dishes) {
		this.dishes = dishes;
	}

	public class Dish {
		
		private String name;
		private float price;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public float getPrice() {
			return price;
		}
		
		public void setPrice(float price) {
			this.price = price;
		}
		
	}
}
