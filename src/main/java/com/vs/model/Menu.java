package com.vs.model;

import java.util.List;

public class Menu {
	
	private String restorant;
	private List<RestorantDish> dishes;
	
	public String getRestorant() {
		return restorant;
	}
	
	public void setRestorant(String restorant) {
		this.restorant = restorant;
	}
	
	public List<RestorantDish> getDishes() {
		return dishes;
	}
	
	public void setDishes(List<RestorantDish> dishes) {
		this.dishes = dishes;
	}

}
