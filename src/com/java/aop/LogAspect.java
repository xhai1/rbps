package com.java.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.java.entity.Log;
import com.java.entity.User;
import com.java.service.LogService;
import com.java.util.IpUtil;
import com.java.util.TimeUtil;

@Aspect
public class LogAspect {

	@Autowired
	private LogService<Log> logService;
	
	/** 
     * 添加业务逻辑方法切入点 
     */ 
	@Pointcut("execution(* com.java.service.*.add*(..))")  
	public void addServiceCall() { }  

	/** 
     * 修改业务逻辑方法切入点 
     */  
    @Pointcut("execution(* com.java.service.*.update*(..))")  
    public void updateServiceCall() { }  
    
    /** 
     * 删除业务逻辑方法切入点 
     * 此处拦截要拦截到具体的莫一个模块
     * 如deleteUser方法。则删除user的时候会记录日志
     * deleteRole时删除role会记录日志
     */  
    @Pointcut("execution(* com.java.service.*.delete*(..))")  
    public void deleteServiceCall() { }  
    
    /** 
     * 管理员添加操作日志(后置通知) 
     * @param joinPoint 
     * @param rtv 
     * @throws Throwable 
     * 
     */  
    @AfterReturning(value="addServiceCall()", argNames="rtv", returning="rtv")  
    public void insertServiceCallCalls(JoinPoint joinPoint, Object rtv) throws Throwable{  
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	User currentUser = (User) request.getSession().getAttribute("currentUser");
        //判断参数  
        if(joinPoint.getArgs() == null){//没有参数  
            return;  
        }  
        //获取方法名  
        //String methodName = joinPoint.getSignature().getName();  
        String className = joinPoint.getArgs()[0].getClass().getName();
        //获取操作内容  
		className = className.substring(className.lastIndexOf(".") + 1);
        String opContent = adminOptionContent(joinPoint.getArgs(), "添加");  
         
        //创建日志对象  
        Log log = new Log();
        log.setModule(className.toLowerCase());
        log.setUserName(currentUser.getUserName()); 
        log.setCreateTime(TimeUtil.formatTime(new Date(),"yyyy-MM-dd HH:mm:ss"));//操作时间  
        log.setContent(opContent);//操作内容  
        log.setOperation("添加");//操作
        log.setIp(IpUtil.getIpAddr(request));
        logService.insertLog(log);
    }  
	
    /**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
	 */
	public String adminOptionContent(Object[] args, String type) throws Exception {
		if (args == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Object info = args[0];
		String className = info.getClass().getName();
		className = className.substring(className.lastIndexOf(".") + 1);
		sb.append(type+className+" 属性名和值：");
		// 获取对象的所有方法
		Method[] methods = info.getClass().getDeclaredMethods();
		// 遍历方法，判断get方法
		for (Method method : methods) {
			String methodName = method.getName();
			// 判断是不是get方法
			if (methodName.indexOf("get") == -1) {// 不是get方法
				continue;// 不处理
			}
			Object rsValue = null;
			try {
				// 调用get方法，获取返回值
				rsValue = method.invoke(info);
				if (rsValue == null) {// 没有返回值
					continue;
				}
			} catch (Exception e) {
				continue;
			}
			// 将值加入内容中
			sb.append(" " + methodName.substring(3) + "-->" + rsValue + "  ");
		}
		return sb.toString();
	}
	
	/** 
     * 管理员修改操作日志(后置通知) 
     * @param joinPoint 
     * @param rtv 
     * @throws Throwable 
     */ 
    @AfterReturning(value="updateServiceCall()", argNames="rtv", returning="rtv")  
    public void updateServiceCallCalls(JoinPoint joinPoint, Object rtv) throws Throwable{  
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	User currentUser = (User) request.getSession().getAttribute("currentUser");
       
          
        //判断参数  
        if(joinPoint.getArgs() == null){//没有参数  
            return;  
        }  
        //获取方法名  
        String className = joinPoint.getArgs()[0].getClass().getName();
		className = className.substring(className.lastIndexOf(".") + 1);
        //获取操作内容  
        String opContent = "修改"+className+"	"+getFieldsInfo(joinPoint.getArgs()[0]);//adminOptionContent(joinPoint.getArgs(), "修改");  
        Log log = new Log();  
        log.setModule(className.toLowerCase());
        log.setUserName(currentUser.getUserName()); 
        log.setCreateTime(TimeUtil.formatTime(new Date(),"yyyy-MM-dd HH:mm:ss"));//操作时间  
        log.setContent(opContent);//操作内容  
        log.setOperation("修改");//操作
        log.setIp(IpUtil.getIpAddr(request));
        logService.insertLog(log);//添加日志  
    }  
    
    /**
	 * 反射获得对象信息
	 * @param o
	 * @return
	 */
	public static String getFieldsInfo(Object o){
		Field[] fields = o.getClass().getDeclaredFields();
		//String[] fieldNames = new String[fields.length];
		StringBuffer sb = new StringBuffer("属性名和值:");
		/**
		 * 遍历所有字段
		 * 从下标1开始
		 * 把0位置的serialVersionUID过滤掉
		 * 所以要求实体类的第一个属性都是serial*UID
		 */
		for (int i = 1; i < fields.length; i++) {    
			sb.append(fields[i].getName()+ "-->"+getFieldValueByName(fields[i].getName(), o)+"  ");
			/**infoMap = new HashMap();
			infoMap.put("type",fields[i].getType().toString());
			infoMap.put("name",fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			list.add(infoMap);*/
		}
		return sb.toString();
	}
	
	/**
	 * 通过反射
	 * 根据字段名称获取字段值
	 * @param fieldName
	 * @param o
	 * @return
	 */
	private static Object getFieldValueByName(String fieldName,Object o){
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();   //手写字母大写
			String getter = "get" + firstLetter + fieldName.substring(1);   // get方法
			Method method = o.getClass().getMethod(getter,new Class[]{});
			Object object = method.invoke(o, new Object[]{});
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
