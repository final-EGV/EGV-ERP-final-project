package org.erp.egv.notice.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.notice.model.dto.NoticeDTO;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<NoticeDTO> noticeAllList(){
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		String jpql = "SELECT n FROM NoticeDTO n";
		TypedQuery<NoticeDTO> query = em.createQuery(jpql, NoticeDTO.class);
		
		List<NoticeDTO> noticeList = query.getResultList();
		
		for (NoticeDTO notice : noticeList) {
			System.out.println(notice);
		}
		
		System.out.println("checkDAO" + noticeList);
		System.out.println("noticeList's size: " + noticeList.size());

		return noticeList;
	}
	
	public NoticeDTO viewNoticeDetail(int noticeCode) {
		NoticeDTO noticeDetail = em.find(NoticeDTO.class, noticeCode);
		   
		System.out.println(noticeCode + " : " + noticeDetail);
		
		return noticeDetail;
	}

	public void insertNotice(NoticeDTO newNotice) {
			EmployeeDTO loginUser = em.find(EmployeeDTO.class, newNotice.getEmployee().getName());
			newNotice.setEmployee(loginUser);

			em.persist(newNotice);
	}

	public Object deleteNotice(int noticeCode) {
		
		System.out.println(noticeCode);
		NoticeDTO deleteNotice = em.find(NoticeDTO.class, noticeCode);
		
		
		em.remove(em.contains(deleteNotice) ? deleteNotice : em.merge(deleteNotice));
		return deleteNotice;
	}

	public void modifyNotice(NoticeDTO noticeModify) {

		NoticeDTO noticeModify1 = em.find(NoticeDTO.class, noticeModify.getNoticeCode());
		
		noticeModify1.setTitle(noticeModify.getTitle());
		noticeModify1.setContent(noticeModify.getContent());
		noticeModify1.setNoticeCode(noticeModify.getNoticeCode());
		noticeModify1.setDate(noticeModify.getDate());
		noticeModify1.setEmployee(noticeModify.getEmployee());
	}

	
}
