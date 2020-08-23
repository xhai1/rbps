package com.java.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.java.util.StringUtil;

public class PrivilegeTag extends TagSupport {

	private static final long serialVersionUID = -532517444654109642L;

	private String operationId; // 对应Attribute,加上set方法。
	private String name;      // 按钮名（添加）
	private String clazz;     // 样式
	private String iconCls;   // 图标
	private String onClick;   // 点击事件
	
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	
	/**
	 * 解析标签，形成原有的a标签
	 * <a href="javascript:reserveRole()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
	 */
	public int doStartTag() throws JspException {
		String currentOperationIds = (String) pageContext.getSession().getAttribute("currentOperationIds");//　获取登录时存储在服务器的用户按钮ID;
		if (StringUtil.isNotEmpty(currentOperationIds) && StringUtil.existStrArr(operationId, currentOperationIds.split(","))) {
			StringBuffer sb = new StringBuffer();
			sb.append("<a href=\"javascript:");
			sb.append(onClick + "\"");
			sb.append("class=\""+clazz+"\"");
			sb.append("iconCls=\""+iconCls+"\"");
			sb.append("plain=\"true\" >");
			sb.append(name +"</a>");
			try {
				pageContext.getOut().write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return EVAL_PAGE;
		}
		return SKIP_BODY; // 跳过body,body部分不会显示
		/* 设置默认值 */
	}

}
