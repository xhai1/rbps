/**
 * 
 */
package com.java.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.dao.OperationDao;
import com.java.service.OperationService;


/******************************************************************************************************************************************************************
 *
 * @Description: （功能描述）
 * 
 * @date: 2020-08-20 11:42:48 （日期）
 * @author: Mr.JHY
 * @version:（版本） 
 *
 ******************************************************************************************************************************************************************/
@Service("operationService")
public class OperationServiceImpl<T> implements OperationService<T> {

	private OperationDao<T> dao;
	public void setDao(OperationDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public List<T> findOperation(T t) throws Exception {
		return dao.findOperation(t);
	}

	@Override
	public int countOperation(T t) throws Exception {
		return dao.countOperation(t);
	}

	@Override
	public void addOperation(T t) throws Exception {
		dao.addOperation(t);
	}

	@Override
	public void updateOperation(T t) throws Exception {
		dao.updateOperation(t);
	}

	@Override
	public void deleteOperation(Integer operationId) throws Exception {
		dao.deleteOperation(operationId);
	}

}
