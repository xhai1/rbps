package com.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java.dao.MenuDao;
import com.java.service.MenuService;
@Service("menuService")
public class MenuServiceImpl<T> implements MenuService<T> {

	private MenuDao<T> dao;
	public void setDao(MenuDao<T> dao) {
		this.dao = dao;
	}
	
	
	@Override
	public List<T> findMenu(T t) throws Exception {
		return dao.findMenu(t);
	}

	@Override
	public int countMenu(T t) throws Exception {
		return dao.countMenu(t);
	}

	@Override
	public void addMenu(T t) throws Exception {
		dao.addMenu(t);
	}

	@Override
	public void updateMenu(T t) throws Exception {
		dao.updateMenu(t);
	}

	@Override
	public void deleteMenu(Integer menuId) throws Exception {
		dao.deleteMenu(menuId);
	}

	@Override
	public List<T> menuTree(Map map) throws Exception {
		return dao.menuTree(map);
	}

	

}
