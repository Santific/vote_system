package com.vs.dao;

import java.util.List;

import com.vs.datamodel.Restorant;


public interface RestorantDao {
	
	void save(Restorant restorant);
	Restorant findRestorant(String restorant);
	List<Restorant> getRestorants();
	public void removeRestorant(Restorant restorant);

}
