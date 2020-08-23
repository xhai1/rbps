package com.java.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.util.CodeUtil;
import com.java.util.IpUtil;
import com.java.util.StochasticUtil;
import com.java.util.StringUtil;
import com.java.util.TimeUtil;
import com.java.util.WriterUtil;

import com.java.entity.Log;
import com.java.entity.Menu;
import com.java.entity.Role;
import com.java.entity.Token;
import com.java.entity.User;
import com.java.service.LogService;
import com.java.service.MenuService;
import com.java.service.RoleService;
import com.java.service.TokenService;
import com.java.service.UserService;

@Controller
@SuppressWarnings("unchecked")
public class LoginController {

	private User user;
	private User currentUser;
	@Autowired
	private UserService<User> userService;
	@Autowired
	private MenuService<Menu> menuService;
	private Role role;
	@Autowired
	private RoleService<Role> roleService;
	private Map map;
	@Autowired
	private LogService<Log> logService;
	private Log log;
	@Autowired
	private TokenService<Token> tokenService;
	private Token token;
	static Logger logger = Logger.getLogger(LoginController.class);

	@SuppressWarnings("static-access")
	@RequestMapping("login")
	public void login(HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			String imageCode=request.getParameter("imageCode");
			String auto = request.getParameter("auto");
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			request.setAttribute("imageCode", imageCode);
			if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
				request.setAttribute("error", "账户或密码为空");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			if(StringUtil.isEmpty(imageCode)){
				request.setAttribute("error", "验证码为空");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			if(!imageCode.equals(session.getAttribute("sRand"))){
				request.setAttribute("error", "验证码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			map = new HashMap<String, String>();
			map.put("userName", userName);
			map.put("password", password);
			currentUser = userService.loginUser(map);
			if(currentUser==null){
				request.setAttribute("error", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				// 加入登陆日志
				log = new Log();
				log.setUserName(userName);
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
				
				// 勾选了两周内自动登录。
				if ("1".equals(auto)) {
					// 记住登录信息
					token = new Token();
					token.setUserId(currentUser.getUserId());
					String userAgent = StochasticUtil.getUUID();
					token.setUserAgent(CodeUtil.getMd5(userAgent, 32));
					token.setCreateTime(TimeUtil.formatTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
					Calendar cal = Calendar.getInstance();
					cal.add(cal.WEEK_OF_YEAR, 2);
					token.setExpireTime(TimeUtil.formatTime(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
					String t = CodeUtil.getMd5(currentUser.getUserName()+CodeUtil.getMd5(userAgent, 32), 32);
					token.setToken(t);
					tokenService.insertToken(token);
					
					// 设置cookie
					Cookie cookie = new Cookie("autoLogin",t);
					cookie.setMaxAge(3600*24*15);  // cookie时效15天
					response.addCookie(cookie);
				}
				// 跳转到主界面
				response.sendRedirect("main.htm");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户登录错误",e);
		}
	}
	
	
	// 进入系统主界面
		@RequestMapping("main")
		public String toMain(){
			return "main";
		}
		
		// 加载最上级左菜单树
		@RequestMapping("menuTree")
		public void getMenuTree(HttpServletRequest request,HttpServletResponse response){
			try {
				String parentId = request.getParameter("parentId");
				currentUser = (User) request.getSession().getAttribute("currentUser");
				role = roleService.findOneRole(currentUser.getRoleId());
				String[] menuIds = role.getMenuIds().split(",");
				map = new HashMap();
				map.put("parentId",parentId);
				map.put("menuIds", menuIds);
				JSONArray jsonArray = getMenusByParentId(parentId, menuIds);
				WriterUtil.write(response, jsonArray.toString());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("加载左菜单错误",e);
			}
		}
		
		// 递归加载所所有树菜单
		public JSONArray getMenusByParentId(String parentId,String[] menuIds)throws Exception{
			JSONArray jsonArray=this.getMenuByParentId(parentId,menuIds);
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject=jsonArray.getJSONObject(i);
				if("open".equals(jsonObject.getString("state"))){
					continue;
				}else{
					jsonObject.put("children", getMenusByParentId(jsonObject.getString("id"),menuIds));
				}
			}
			return jsonArray;
		}
		
		// 将所有的树菜单放入easyui要求格式的json
		public JSONArray getMenuByParentId(String parentId,String[] menuIds)throws Exception{
			JSONArray jsonArray=new JSONArray();
			map= new HashMap();
			map.put("parentId",parentId);
			map.put("menuIds", menuIds);
			List<Menu> list = menuService.menuTree(map);
			for(Menu menu : list){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", menu.getMenuId());
				jsonObject.put("text", menu.getMenuName());
				jsonObject.put("iconCls", menu.getIconCls());
				JSONObject attributeObject = new JSONObject();
				attributeObject.put("menuUrl", menu.getMenuUrl());
				if(!hasChildren(menu.getMenuId(), menuIds)){
					jsonObject.put("state", "open");
				}else{
					jsonObject.put("state", menu.getState());				
				}
				jsonObject.put("attributes", attributeObject);
				jsonArray.add(jsonObject);
			}
			return jsonArray;
		}
		
		// 判断是不是有子孩子，人工结束递归树
		public boolean hasChildren(Integer parentId,String[] menuIds){
			boolean flag = false;
			try {
				map= new HashMap();
				map.put("parentId",parentId);
				map.put("menuIds", menuIds);
				List<Menu> list = menuService.menuTree(map);
				if (list == null || list.size()==0) {
					flag = false;
				}else {
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("加载左菜单时判断是不是有子孩子错误",e);
			}
			return flag;
		}
		
		// 更新密码
		@RequestMapping("updatePassword")
		public void updatePassword(HttpServletRequest request,HttpServletResponse response){
			JSONObject result=new JSONObject();
			try {
				String userId=request.getParameter("userId");
				String newPassword=request.getParameter("newPassword");
				user=new User();
				user.setUserId(Integer.parseInt(userId));
				user.setPassword(newPassword);
				userService.updateUser(user);
				result.put("success", "true");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("密码更新失败",e);
				result.put("success", "true");
				result.put("errorMsg", "对不起！密码修改失败");
			}
			WriterUtil.write(response, result.toString());
		}
		
		//安全退出
		@SuppressWarnings("unused")
		@RequestMapping("logout")
		private void logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
			
			// 记录日志
			HttpSession session = request.getSession();
			log.setUserName(((User)session.getAttribute("currentUser")).getUserName());
			log.setCreateTime(TimeUtil.formatTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
			log.setOperation("退出");
			logService.insertLog(log);
			
			// 清空session
			session.invalidate();
			
			// 清空cookie
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = new Cookie(cookies[i].getName(), null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

			response.sendRedirect("login.jsp");
		}
		
		@RequestMapping("auto")
		public void autoLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    	Cookie[] cookies = request.getCookies();
	    	if(cookies == null) {
	    		request.getRequestDispatcher("login.jsp").forward(request, response);
	    		return;
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
							 // request.getRequestDispatcher("login.jsp").forward(request, response);
							  return;
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
								HttpSession session = request.getSession();
								session.setAttribute("currentUser", currentUser);  // 当前用户信息
								session.setAttribute("currentOperationIds", role.getOperationIds());  // 当前用户所拥有的按钮权限
								
								// 跳转到主界面
								WriterUtil.write(response, "ok");
						  }
	           		}
	        	}
	    	}	   
		}
		
}
