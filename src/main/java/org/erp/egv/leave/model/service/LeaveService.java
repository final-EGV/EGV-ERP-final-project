package org.erp.egv.leave.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.leave.model.dao.LeaveDAO;
import org.erp.egv.leave.model.dto.AnnualLeaveDTO;
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

	@Transactional
	public List<AnnualLeaveDTO> EmpLeaveList() {
		return leaveDAO.EmpLeaveList();
	}

	@Transactional
	public List<UseAnnualLeaveDTO> EmpUsedLeave() {
		return leaveDAO.EmpUsedLeave();
	}
	
	@Transactional
	public List<UseAnnualLeaveDTO> EmpUsedLeaveList(String code) {
		return leaveDAO.EmpUsedLeaveList(code);
	}
}
