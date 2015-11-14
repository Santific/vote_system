package com.vs.datamodel;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restorant", catalog = "public")
public class Restorant implements Serializable {

	private String name;
	private Collection<Dish> dishes;
	private Collection<Vote> votes;

	@Id
	@Column(name = "name", unique = true, nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy="dishKey.restorantName", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Collection<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Collection<Dish> dishes) {
		this.dishes = dishes;
	}
	
	@OneToMany(mappedBy="restorantName", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Collection<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Collection<Vote> votes) {
		this.votes = votes;
	}
	
}
