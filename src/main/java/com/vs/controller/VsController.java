package com.vs.controller;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vs.datamodel.Restorant;
import com.vs.exception.DishNotFoundException;
import com.vs.exception.RestorantAlreadyExistsException;
import com.vs.exception.RestorantNotFoundException;
import com.vs.model.AddDishResult;
import com.vs.model.VoteResult;
import com.vs.service.MenuService;
import com.vs.service.CheckStatusService;
import com.vs.service.VoteService;

@Controller
public class VsController {
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	CheckStatusService checkMenuService;
	
	@Autowired
	VoteService voteService;
			
	@ResponseBody
	@RequestMapping(value = "/admin/addrestorant", method = RequestMethod.GET)
    public String addRestorant(@RequestParam("restorant") String restorant) {
		try {
			menuService.addRestorant(restorant);
		} catch (RestorantAlreadyExistsException e) {
			return "already exists";
		}
		return "added";
    }
	
	@ResponseBody
	@RequestMapping(value = "/admin/removerestorant", method = RequestMethod.GET)
    public String removeRestorant(@RequestParam("restorant") String restorant) {
		try {
			menuService.removeRestorant(restorant);
		} catch (RestorantNotFoundException e) {
			return "no such restorant";
		}
		return "removed";
    }
	
	@ResponseBody
	@RequestMapping(value = "/admin/adddish", method = RequestMethod.GET)
    public String addDish(@RequestParam("restorant") String restorant,
    		@RequestParam("name") String dishName, @RequestParam("price") Float price) {
		try {
			AddDishResult addDishResult = menuService.addDish(restorant, dishName, price);
			switch (addDishResult) {
			case DISH_UPDATED:
				return "dish updated";
			case DISH_ADDED:
				return "added";
			}
			return "---";
		} catch (RestorantNotFoundException e) {
			return "restorant not found";
		}
    }
	
	@ResponseBody
	@RequestMapping(value = "/admin/removedish", method = RequestMethod.GET)
    public String removeDish(@RequestParam("restorant") String restorant,
    		@RequestParam("name") String dishName) {
		try {
			menuService.removeDish(restorant, dishName);
		} catch (DishNotFoundException e) {
			return "no such dish or restorant found";
		}
		return "dish removed";
    }
	
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String help() {
        return "help";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String index() {
        return "logout";
    }
    
	@ResponseBody
	@RequestMapping(value = "/user/status", method = RequestMethod.GET)
	public Collection<Restorant> getStatus() throws JsonProcessingException {
		return checkMenuService.getStatus();
	}
    
	@ResponseBody
	@RequestMapping(value = "/user/vote", method = RequestMethod.GET)
	public String vote(@RequestParam("restorant") String restorant) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			VoteResult voteResult = voteService.vote(restorant, userName);
			switch (voteResult) {
			case VOTE_ACCEPTED:
				return "vote accepted";
			case VOTE_REJECTED:
				return "to late for vote";
			case VOTE_UPDATED:
				return "vote updated";
			}
		} catch (RestorantNotFoundException e) {
			return "restorant not found";
		}
		return "vote accepted";
	}

	@ResponseBody
	@ExceptionHandler({SQLException.class, DataAccessException.class})
	public String databaseError() {
	    return "databaseError";
	}

	@ResponseBody
	@ExceptionHandler(Throwable.class)
	public String handleError(HttpServletRequest req, Exception exception) {
	    return "error";
	}
	  
}
