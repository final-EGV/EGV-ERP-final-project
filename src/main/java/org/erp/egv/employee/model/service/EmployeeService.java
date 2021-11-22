package org.erp.egv.employee.model.service;

import java.util.ArrayList;
import java.util.List;

import org.erp.egv.employee.model.dao.EmpInfoDAO;
import org.erp.egv.employee.model.dto.AuthorityDTO;
import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.EmployeeRoleDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements UserDetailsService {
	
	private EmpInfoDAO employeeDAO;

	@Autowired
	public EmployeeService(EmpInfoDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	
	/* 사용자 정보를 조회해서 UserDetails 타입을 반환하는 메소드 */
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		/* 우리가 만든 MemberDTO를 쓸 수 없고 User객체를 반환해야 한다. */
		EmployeeDTO employee = employeeDAO.findMemberById(name);

		if (employee == null) {
			employee = new EmployeeDTO();
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		if (employee.getEmployeeRoleList() != null) {
			List<EmployeeRoleDTO> roleList = employee.getEmployeeRoleList();

			for (int i = 0; i < roleList.size(); i++) {
				AuthorityDTO authority = roleList.get(i).getAuthority();

				authorities.add(new SimpleGrantedAuthority(authority.getName()));
			}
		}
		
		UserImpl user = new UserImpl(employee.getCode(), employee.getPwd(), authorities);
		user.setDetails(employee);
		
		return user;
	}

}
