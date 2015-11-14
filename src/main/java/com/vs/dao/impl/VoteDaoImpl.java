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

import com.vs.dao.VoteDao;
import com.vs.datamodel.Vote;
import com.vs.datamodel.Vote.VoteKey;

@Repository("voteDao")
public class VoteDaoImpl implements VoteDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	public void save(Vote vote) {
		em.persist(vote);
	}

	@Override
	public Vote findVote(VoteKey voteKey) {
		return em.find(Vote.class, voteKey);
	}

	@Override
	public Collection<Vote> getVotes(String restorantName, Date date) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vote> cq = cb.createQuery(Vote.class);
        Root<Vote> r = cq.from(Vote.class);
        CriteriaQuery<Vote> q = cq.select(r)
        		.where(cb.and(cb.equal(r.get("restorantName"), restorantName)),
        				cb.equal(r.get("key").get("date"), date));
        List<Vote> restorants = em.createQuery(q).getResultList();
        return restorants;		
	}

}
