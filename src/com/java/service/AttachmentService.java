package com.java.service;

import java.util.List;

public interface AttachmentService<T> {

	public abstract void insertAttachment(T t) throws Exception;
	
	public abstract List<T> findAttachment(T t) throws Exception;
	
	public abstract int countAttachment(T t) throws Exception;
	
}
