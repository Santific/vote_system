package com.vs.datamodel;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vote", catalog = "public")
public class Vote implements Serializable {

	@EmbeddedId
	private VoteKey key;
	private String restorantName;

	public String getRestorantName() {
		return restorantName;
	}

	public void setRestorantName(String restorantName) {
		this.restorantName = restorantName;
	}

	public VoteKey getKey() {
		return key;
	}

	public void setKey(VoteKey key) {
		this.key = key;
	}

	@Embeddable
	public static class VoteKey implements Serializable {

		private String voteUser;
		private Date date;
		
		@Column(name = "vote_user", nullable = false)
		public String getVoteUser() {
			return voteUser;
		}
		
		public void setVoteUser(String voteUser) {
			this.voteUser = voteUser;
		}
		
		@Column(name = "date", nullable = false)
		public Date getDate() {
			return date;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime * result + ((voteUser == null) ? 0 : voteUser.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			VoteKey other = (VoteKey) obj;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (voteUser == null) {
				if (other.voteUser != null)
					return false;
			} else if (!voteUser.equals(other.voteUser))
				return false;
			return true;
		}

	}

}
