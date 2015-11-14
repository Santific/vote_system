package com.vs.dao;

import java.util.Collection;
import java.util.Date;

import com.vs.datamodel.Vote;
import com.vs.datamodel.Vote.VoteKey;

public interface VoteDao {

	void save(Vote vote);
	Vote findVote(VoteKey voteKey);
	Collection<Vote> getVotes(String restorantName, Date date);

}
