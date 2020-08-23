package com.java.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.java.entity.Log;
import com.java.entity.Role;
import com.java.entity.Token;
import com.java.entity.User;
import com.java.service.LogService;
import com.java.service.RoleService;
import com.java.service.TokenService;
import com.java.service.UserService;
import com.java.util.IpUtil;
import com.java.util.TimeUtil;
/**
 *@author: wangq
 *@date: 2015-8-15下午12:39:50
 *@version:
 *@description：登录拦截器
 */
@SuppressWarnings("unchecked")
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private Map map;
	private Log log;
	private Token token;
	private Role role;
	@Autowired
	private RoleService<Role> roleService;
	@Autowired
	private UserService<User> userService;
	@Autowired
	private LogService<Log> logService;
	@Autowired
	private TokenService<Token> tokenService;
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 请求的路径

		String url = request.getServletPath().toString();
		HttpSession session = request.getSession();
		User currentUser = ((User) session.getAttribute("currentUser"));
		if (currentUser == null) {
			if (url.contains("login") || url.contains("Login")) {
				return true;
			}
			Cookie[] cookies = request.getCookies();
	    	if(cookies == null) {
	    		request.getRequestDispatcher("login.jsp").forward(request, response);
	    		return true;
	    	} else {
	        	for(int i=0; i<cookies.length; i++) {
	           		Cookie cookie = cookies[i];
	           		if("autoLogin".equals(cookie.getName())){
						  String value = cookie.getValue();
						  map = new HashMap();
						  map.put("token", value);
						  map.put("expireTime", TimeUtil.formatTime(new Date(),"yyyy-MM-dd HH:mm:ss"));
						  token = tokenService.findOneToken(map);
						  if (token==null) {
							  request.getRequestDispatcher("login.jsp").forward(request, response);
							  return true;
						  } else {
							  	int userId = token.getUserId();
							  	currentUser = userService.findOneUser(userId);
							  	log = new Log();
								log.setUserName(currentUser.getUserName());
								log.setCreateTime(TimeUtil.formatTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
								log.setIp(IpUtil.getIpAddr(request));
								log.setOperation("登录");
								logService.insertLog(log);
								
								// 登录信息存入session
								role = roleService.findOneRole(currentUser.getRoleId());
								String roleName=role.getRoleName();
								currentUser.setRoleName(roleName);
								session.setAttribute("currentUser", currentUser);  // 当前用户信息
								session.setAttribute("currentOperationIds", role.getOperationIds());  // 当前用户所拥有的按钮权限
								
								request.getRequestDispatcher("login.jsp").forward(request, response);
								return true;
						  }
	           		}
	        	}
	    	}
			return true;
		}
		return true;
	}

}
