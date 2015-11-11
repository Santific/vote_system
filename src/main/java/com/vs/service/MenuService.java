package com.vs.service;

import com.vs.exception.RestorantAlreadyExistsException;
import com.vs.exception.DishNotFoundException;
import com.vs.exception.RestorantNotFoundException;
import com.vs.model.AddDishResult;

public interface MenuService {

	void addRestorant(String restorant) throws RestorantAlreadyExistsException;

	AddDishResult addDish(String restorant, String dishName, Float price)
			throws RestorantNotFoundException;

	void removeRestorant(String restorant) throws RestorantNotFoundException;

	void removeDish(String restorant, String dishName) throws DishNotFoundException;

}
