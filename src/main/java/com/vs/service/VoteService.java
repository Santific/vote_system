package com.vs.service;

import com.vs.exception.RestorantNotFoundException;
import com.vs.model.VoteResult;

public interface VoteService {

	VoteResult vote(String restorant, String userName) throws RestorantNotFoundException;

}
