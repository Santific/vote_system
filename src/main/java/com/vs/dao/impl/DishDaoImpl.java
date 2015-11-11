package com.vs.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vs.dao.DishDao;
import com.vs.datamodel.Dish;

@Repository("dishDao")
public class DishDaoImpl implements DishDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public Dish findDish(Dish.DishKey dishKey) {
	      return em.find(Dish.class, dishKey);
	}

	@Override
	public void save(Dish dish) {
		em.persist(dish);
	}
	
	@Override
	public void removeDish(Dish dish) {
		em.remove(dish);		
		
	}

}
