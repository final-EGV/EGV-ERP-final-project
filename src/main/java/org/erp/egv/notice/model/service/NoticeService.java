package org.erp.egv.notice.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.notice.model.dao.NoticeDAO;
import org.erp.egv.notice.model.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
	
	private NoticeDAO noticeDAO;
	
	@Autowired
	public NoticeService(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	@Transactional
	public List<NoticeDTO> noticeAllList() {
		
		return noticeDAO.noticeAllList();
	}
	
	@Transactional
	public void insertNotice(NoticeDTO newNotice) {
		noticeDAO.insertNotice(newNotice);
	}

	@Transactional
	public NoticeDTO selectNoticeDetail(int noticeCode) {
		return noticeDAO.viewNoticeDetail(noticeCode);
	}

	@Transactional
	public void deleteNotice(int noticeCode) {
		
		noticeDAO.deleteNotice(noticeCode); 
	}

	@Transactional
	public void modifyNotice(NoticeDTO noticeModify) {
		
		noticeDAO.modifyNotice(noticeModify);
	}
	

}