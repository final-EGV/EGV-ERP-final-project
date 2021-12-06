package org.erp.egv.leave.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.leave.model.dao.LeaveDAO;
import org.erp.egv.leave.model.dto.AnnualLeaveCategoryDTO;
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

	@Transactional
	public EmployeeDTO findEmp(String code) {
		return leaveDAO.findEmp(code);
	}

	@Transactional
	public void addLeave(UseAnnualLeaveDTO leave) {
		leaveDAO.addLeave(leave);
	}

	@Transactional
	public AnnualLeaveCategoryDTO findCategory(int code) {
		return leaveDAO.findCategory(code);
	}

	@Transactional
	public void modifyLeave(String empCode, int total) {
		leaveDAO.modifyLeave(empCode, total);
	}

	@Transactional
	public AnnualLeaveDTO selectSingleLeave(String code) {
		return leaveDAO.selectSingleLeave(code);
	}
}
