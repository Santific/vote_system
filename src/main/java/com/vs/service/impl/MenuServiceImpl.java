package com.vs.service.impl;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.vs.dao.DishDao;
import com.vs.dao.RestorantDao;
import com.vs.datamodel.Dish;
import com.vs.datamodel.Restorant;
import com.vs.exception.DishNotFoundException;
import com.vs.exception.RestorantAlreadyExistsException;
import com.vs.exception.RestorantNotFoundException;
import com.vs.model.AddDishResult;
import com.vs.service.MenuService;


@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	RestorantDao restorantDao;

	@Autowired
	DishDao dishDao;

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void addRestorant(String restorantName) throws RestorantAlreadyExistsException {
		
		Restorant restorant = restorantDao.findRestorant(restorantName);
		
		if (restorant == null) {
			restorant = new Restorant();
			restorant.setName(restorantName);
			restorantDao.save(restorant);
		} else {
			throw new RestorantAlreadyExistsException();
		}
		
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public AddDishResult addDish(String restorantName, String dishName, Float price)
			throws RestorantNotFoundException {
		
		Restorant restorant = restorantDao.findRestorant(restorantName);

		if (restorant == null) {
			throw new RestorantNotFoundException();
		}
		
		LocalDateTime dateTime = LocalDateTime.now();
		Date date = Date.valueOf(dateTime.toLocalDate());

		Dish.DishKey dishKey = new Dish.DishKey();
		dishKey.setDishName(dishName);
		dishKey.setDate(date);
		dishKey.setRestorantName(restorant.getName());

		Dish dish = dishDao.findDish(dishKey);
		
		if (dish == null) {
			dish = new Dish();
			
			dish.setPrice(price);
			dish.setDishKey(dishKey);
			
			dishDao.save(dish);
			return AddDishResult.DISH_ADDED;
		} else {
			dish.setPrice(price);
			dish.setDishKey(dishKey);
			return AddDishResult.DISH_UPDATED;

		}
				
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void removeRestorant(String restorantName)
			throws RestorantNotFoundException {

		Restorant restorant = restorantDao.findRestorant(restorantName);

		if (restorant == null) {
			throw new RestorantNotFoundException();
		}
		
		restorantDao.removeRestorant(restorant);
		
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void removeDish(String restorantName, String dishName)
			throws DishNotFoundException {

		LocalDateTime dateTime = LocalDateTime.now();
		Date date = Date.valueOf(dateTime.toLocalDate());

		Dish.DishKey dishKey = new Dish.DishKey();
		dishKey.setDishName(dishName);
		dishKey.setDate(date);
		dishKey.setRestorantName(restorantName);

		Dish dish = dishDao.findDish(dishKey);
		
		if (dish == null) {
			throw new DishNotFoundException();
		} else {
			dishDao.removeDish(dish);
		}
	}
	
}
