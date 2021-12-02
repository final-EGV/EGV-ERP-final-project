package org.erp.egv.leave.model.service;

import java.util.List;

import org.erp.egv.leave.model.dao.LeaveDAO;
import org.erp.egv.leave.model.dto.UseAnnualLeaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveService {

	private LeaveDAO leaveDAO;
	
	@Autowired
	public LeaveService(LeaveDAO leaveDAO) {
		this.leaveDAO = leaveDAO;
	}

	public List<UseAnnualLeaveDTO> leaveList() {
		// TODO Auto-generated method stub
		return null;
	}
}
