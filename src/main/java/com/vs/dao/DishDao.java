package com.vs.dao;

import java.util.Collection;
import java.util.Date;

import com.vs.datamodel.Dish;
import com.vs.datamodel.Dish.DishKey;

public interface DishDao {

	Dish findDish(DishKey dishKey);
	void save(Dish dish);
	void removeDish(Dish dish);
	Collection<Dish> getDishes(String restorantName, Date date);

}
