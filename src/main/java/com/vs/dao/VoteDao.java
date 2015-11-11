package com.vs.dao;

import com.vs.datamodel.Vote;
import com.vs.datamodel.Vote.VoteKey;

public interface VoteDao {

	void save(Vote vote);

	Vote findVote(VoteKey voteKey);

}
