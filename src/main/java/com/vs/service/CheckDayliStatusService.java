package com.vs.service;

import java.util.Collection;

import com.vs.model.Menu;
import com.vs.model.RestorantVotes;

public interface CheckDayliStatusService {

	Collection<Menu> getDailyRestorantsMenus();
	Collection<RestorantVotes> getDailyRestorantsVotes();

}
