package org.erp.egv.commu.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.commu.model.dao.CommuDAO;
import org.erp.egv.commu.model.dto.BlindPostDTO;
import org.erp.egv.commu.model.dto.CmtDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommuService {
	
	private CommuDAO commudao;
	
	@Autowired
	public CommuService(CommuDAO commudao) {
		this.commudao = commudao;
	}
	
	@Transactional
	public List<BlindPostDTO> selectBlindList() {
		return commudao.selectBlindList();
	}

	@Transactional
	public BlindPostDTO selectBlindPost(int code) {
		return commudao.selectBlindPost(code);
	}

	@Transactional
	public void insertBlindPost(BlindPostDTO blindPost) {
		commudao.insertBlindPost(blindPost);
	}

	@Transactional
	public void insertPostCmt(CmtDTO postCmt) {
		commudao.insertPostCmt(postCmt);
	}

	@Transactional
	public void deleteCmt(int code) {
		commudao.deleteCmt(code);
	}

	@Transactional
	public void postDelete(int code) {
		BlindPostDTO post = commudao.selectBlindPost(code);
		if(post.getCmt() != null) {
			for (CmtDTO cmt : post.getCmt()) {
				commudao.deleteCmt(cmt.getCode());
			}
		}
		commudao.postDelete(code);
	}

	@Transactional
	public void updateBlindPost(int code, String title, String content) {
		commudao.updateBlindPost(code, title, content);
	}

	@Transactional
	public List<BlindPostDTO> selectMyBlindList(String code) {
		return commudao.selectMyBlindList(code);
	}

}
