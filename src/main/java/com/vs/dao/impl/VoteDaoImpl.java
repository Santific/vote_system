package com.vs.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
