package org.erp.egv.commu.model.service;

import java.util.List;

import org.erp.egv.commu.model.dao.CommuDAO;
import org.erp.egv.commu.model.dto.BlindPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommuService {
	
	private CommuDAO commudao;
	
	@Autowired
	public CommuService(CommuDAO commudao) {
		this.commudao = commudao;
	}
	

	public List<BlindPostDTO> selectBlindList() {
		return commudao.selectBlindList();
	}


	public BlindPostDTO selectBlindPost(int code) {
		return commudao.selectBlindPost(code);
	}

}
