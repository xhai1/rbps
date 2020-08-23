package com.java.entity;

import java.io.Serializable;

/**
 *@author: wangq
 *@date: 2015-5-18下午04:51:51
 *@version:
 *@description：
 */
public class Role extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer roleId;
	private String roleName;
	private String menuIds;
	private String operationIds;
	private String roleDescription;
	
	
	
	public String getOperationIds() {
		return operationIds;
	}
	public void setOperationIds(String operationIds) {
		this.operationIds = operationIds;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
}
