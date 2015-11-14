package com.vs.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.vs.dao.RestorantDao;
import com.vs.datamodel.Restorant;


@Repository("restorantDao")
public class RestorantDaoImpl implements RestorantDao {

    @PersistenceContext
    private EntityManager em;

    @Override
	public void save(Restorant restorant) {
		em.persist(restorant);
	}

	@Override
	public Restorant findRestorant(String restorant) {
      return em.find(Restorant.class, restorant);
	}

	@Override
	public List<Restorant> getRestorants() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Restorant> cq = cb.createQuery(Restorant.class);
        Root<Restorant> rootEntry = cq.from(Restorant.class);
        CriteriaQuery<Restorant> all = cq.select(rootEntry);
        List<Restorant> restorants = em.createQuery(all).getResultList();
        return restorants;		
	}

	@Override
	public void removeRestorant(Restorant restorant) {
		em.remove(restorant);		
	}

}
