package com.vs.dao;

import com.vs.datamodel.Dish;
import com.vs.datamodel.Dish.DishKey;

public interface DishDao {

	Dish findDish(DishKey dishKey);

	void save(Dish dish);

	void removeDish(Dish dish);

}
