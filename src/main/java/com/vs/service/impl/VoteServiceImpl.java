package com.vs.service.impl;


import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.vs.dao.RestorantDao;
import com.vs.dao.VoteDao;
import com.vs.datamodel.Restorant;
import com.vs.datamodel.Vote;
import com.vs.datamodel.Vote.VoteKey;
import com.vs.exception.RestorantNotFoundException;
import com.vs.model.VoteResult;
import com.vs.service.VoteService;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

	@Autowired
	RestorantDao restorantDao;
	
	@Autowired
	VoteDao voteDao;
	
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public VoteResult vote(String restorantName, String userName) throws RestorantNotFoundException {


		Restorant restorant = restorantDao.findRestorant(restorantName);

		if (restorant == null) {
			throw new RestorantNotFoundException();
		}
		
		LocalDateTime dateTime = LocalDateTime.now();
		Date date = Date.valueOf(dateTime.toLocalDate());

		VoteKey voteKey = new VoteKey();
		voteKey.setVoteUser(userName);
		voteKey.setDate(date);
		
		Vote vote = voteDao.findVote(voteKey);
		if ((dateTime.toLocalTime().getHour() >= 11) && (vote != null)) {
			return VoteResult.VOTE_REJECTED;
		}
		
		if (vote == null) {
			vote = new Vote();
			vote.setKey(voteKey);
			vote.setRestorantName(restorant.getName());
			voteDao.save(vote);
			return VoteResult.VOTE_ACCEPTED;
		} else {
			return VoteResult.VOTE_UPDATED;
		}		
		
	}
	
}
