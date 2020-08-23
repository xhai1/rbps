package com.java.entity;

import java.io.Serializable;

/**
 *@author: wangq
 *@date: 2015-8-6下午05:17:43
 *@version: 
 *@description：操作按钮所拥有的属性
 */
public class Operation extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer operationId;  //按钮ID
	private Integer menuId;       //所属哪一个页面菜单的ID
	private String operationName;  //按钮名称
	private String menuName;
	
	
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getOperationId() {
		return operationId;
	}
	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
}
