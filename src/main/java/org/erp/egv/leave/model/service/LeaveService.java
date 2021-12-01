package org.erp.egv.leave.model.service;

import org.erp.egv.leave.model.dao.LeaveDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveService {

	private LeaveDAO leaveDAO;
	
	@Autowired
	public LeaveService(LeaveDAO leaveDAO) {
		this.leaveDAO = leaveDAO;
	}
}
