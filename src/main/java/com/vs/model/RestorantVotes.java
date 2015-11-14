package com.vs.model;

import java.util.Collection;

public class RestorantVotes {

	private String restorantName;
	private Collection<String> votedUsers;

	public Collection<String> getVotedUsers() {
		return votedUsers;
	}

	public void setVotedUsers(Collection<String> votedUsers) {
		this.votedUsers = votedUsers;
	}

	public String getRestorantName() {
		return restorantName;
	}

	public void setRestorantName(String restorantName) {
		this.restorantName = restorantName;
	}

}
