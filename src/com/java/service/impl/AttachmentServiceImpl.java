package com.java.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.dao.AttachmentDao;
import com.java.service.AttachmentService;

@Service("attachmentService")
public class AttachmentServiceImpl<T> implements AttachmentService<T> {

	private AttachmentDao<T> dao;
	public void setDao(AttachmentDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public void insertAttachment(T t) throws Exception {
		dao.insertAttachment(t);
	}

	@Override
	public List<T> findAttachment(T t) throws Exception {
		return dao.findAttachment(t);
		
	}

	@Override
	public int countAttachment(T t) throws Exception {
		return dao.countAttachment(t);
	}

	

}
