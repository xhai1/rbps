package com.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java.dao.RoleDao;
import com.java.service.RoleService;
@Service("roleService")
public class RoleServiceImpl<T> implements RoleService<T> {

	private RoleDao<T> dao;
	public void setDao(RoleDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public T findOneRole(Integer roleId) throws Exception {
		return dao.findOneRole(roleId);
	}

	@Override
	public List<T> findRole(T t) throws Exception {
		return dao.findRole(t);
	}

	@Override
	public int countRole(T t) throws Exception {
		return dao.countRole(t);
	}

	@Override
	public void deleteRole(Integer roleId) throws Exception {
		dao.deleteRole(roleId);
	}

	@Override
	public void addRole(T t) throws Exception {
		dao.addRole(t);
	}

	@Override
	public void updateRole(T t) throws Exception {
		dao.updateRole(t);		
	}

	@Override
	public T existRoleWithRoleName(String roleName) throws Exception {
		return dao.existRoleWithRoleName(roleName);
	}

	@Override
	public void deleteRoleByRoleIds(Map map) throws Exception {
		dao.deleteRoleByRoleIds(map);
	}

	

}
