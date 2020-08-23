package com.java.dao;

import java.util.List;

import org.mybatis.spring.annotation.Mapper;

@Mapper("AttachmentDao")
public interface AttachmentDao <T>{

	public abstract void insertAttachment(T t) throws Exception; 
	
	public abstract List<T> findAttachment(T t) throws Exception;
	
	public abstract int countAttachment(T t) throws Exception;
	
}
