package com.vs.dao.impl;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

	@Override
	public Collection<Dish> getDishes(String restorantName, Date date) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Dish> cq = cb.createQuery(Dish.class);
        Root<Dish> r = cq.from(Dish.class);
        CriteriaQuery<Dish> q = cq.select(r)
        		.where(cb.and(cb.equal(r.get("dishKey").get("restorantName"), restorantName)),
        				cb.equal(r.get("dishKey").get("date"), date));
        List<Dish> restorants = em.createQuery(q).getResultList();
        return restorants;		
	}

}
