package com.vs.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.vs.dao.RestorantDao;
import com.vs.datamodel.Restorant;
import com.vs.service.CheckStatusService;

@Service("checkStatusService")
public class CheckStatusServiceImpl implements CheckStatusService {

	@Autowired
	RestorantDao restorantDao;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Collection<Restorant> getStatus() {
		return restorantDao.getRestorants();
	}

}
