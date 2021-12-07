package org.erp.egv.official.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.official.model.dao.OfficialDAO;
import org.erp.egv.official.model.dto.OfficialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficialService {
	
	private OfficialDAO officialDAO;
	
	@Autowired
	public OfficialService(OfficialDAO officialDAO) {
		this.officialDAO = officialDAO;
	}

	public List<OfficialDTO> officialListRequest() {
		return officialDAO.officialListRequest();
	}
	
	@Transactional 
	public void officialContentWrite(OfficialDTO officialDTO) {
		officialDAO.officialContentWrite(officialDTO);
	}

	public OfficialDTO officialDetailRequest(int code) {
		return officialDAO.officialDetailRequest(code);
	}

	public int findNextCode() {
		return officialDAO.findNextCode();
	}
	
}
