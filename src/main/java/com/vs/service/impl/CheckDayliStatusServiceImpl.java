package com.vs.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.vs.dao.DishDao;
import com.vs.dao.RestorantDao;
import com.vs.dao.VoteDao;
import com.vs.datamodel.Dish;
import com.vs.datamodel.Restorant;
import com.vs.datamodel.Vote;
import com.vs.model.Menu;
import com.vs.model.RestorantVotes;
import com.vs.service.CheckDayliStatusService;

@Service("checkDayliStatusService")
public class CheckDayliStatusServiceImpl implements CheckDayliStatusService {

	@Autowired
	VoteDao voteDao;
	
	@Autowired
	DishDao dishDao;
	
	@Autowired
	RestorantDao restorantDao;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Collection<Menu> getDailyRestorantsMenus() {

		LocalDate localDate = LocalDate.now();
		
		List<Menu> menus = new LinkedList<Menu>();
		Collection<Restorant> restorants = restorantDao.getRestorants();
		for (Restorant restorant : restorants) {
			Menu menu = new Menu();
			menu.setRestorantName(restorant.getName());
			LinkedList<Menu.Dish> dishes = new LinkedList<Menu.Dish>();
			for (Dish dish : dishDao.getDishes(restorant.getName(), Date.valueOf(localDate))) {
				Menu.Dish dish_ = menu.new Dish();
				dish_.setName(dish.getKey().getDishName());
				dish_.setPrice(dish.getPrice());
				dishes.add(dish_);
			}
			menu.setDishes(dishes);
			menus.add(menu);
		}
		return menus;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Collection<RestorantVotes> getDailyRestorantsVotes() {
		
		LocalDate localDate = LocalDate.now();
		
		List<RestorantVotes> restorantsVotes = new LinkedList<RestorantVotes>();
		Collection<Restorant> restorants = restorantDao.getRestorants();
		for (Restorant restorant : restorants) {
			RestorantVotes votes = new RestorantVotes();
			votes.setRestorantName(restorant.getName());
			LinkedList<String> votedUsers = new LinkedList<String>();
			for (Vote vote : voteDao.getVotes(restorant.getName(), Date.valueOf(localDate))) {
				votedUsers.add(vote.getKey().getVoteUser());
			}
			votes.setVotedUsers(votedUsers);
			restorantsVotes.add(votes);
		}
		return restorantsVotes;
	}

}
