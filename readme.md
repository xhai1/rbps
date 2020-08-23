
1	系统采用的是spring4.0.2+springmvc+mybatis3.2.6+maven3.3.3+easyui1.3.3框架。

2 	系统的环境是myeclipse10+jdk1.7.0_80+tomcat7.0.64+win7 64+maven3.3.3
     本机运行环境　
     Eclipse Java EE IDE for Web Developers.
	Version: Photon Release (4.8.0)
	Build id: 20180619-1200
	OS: Linux, v.4.17.0-kali1-amd64, x86_64 / gtk 3.24.13, WebKit 2.24.3
	Java version: 1.8.0_211
	Apache Tomcat/8.0.53
	MySql 8.0.16
3 	主要功能包括
     
     1）用户的增删改查
     
     	   项目中用到了springmvc的拦截器，只有登录才能操作   主管理员账号admin   密码1  为方便起见，数据库密码未加密
     
     2）角色的增删改查和授权
        
           实现页面菜单的权限用的是easyui的tree。按钮级别的权限用的是自定义的JSP标签
     
     3）页面菜单的管理
     
     4）操作日志的记录和备份
     
          采用的方法是利用aop拦截add,update方法来实现操作记录。
          
          同时用了corn表达式来实现自动备份。
          
     5）记住登录（暂未实现）
     
           当用户勾选了记住密码时，再用户登录后，通过将用户名以及其他信息加密cookie存一份，
           
           数据库存一份来(参与加密的源数据不包含密码)
           
4	由于论坛对源码大小的限制，临时将项目改成maven的。

5	联系作者  如果有写的不好的地方，欢迎大家一起讨论。
 
 6.练习小结
          
menu.jsp 小图标地址,合成图片(从左到有icon-1 、icon-2、icon-3...)
http://localhost:8080/rbps/jquery-easyui-1.3.3/themes/tabicons.gif

本项目菜单、按钮的删除并没有更新角色的对应菜单ID与按钮ID.
 修改权限后需用户再次登录生效        
 
 LogAspect中删除操作没有处理，传递的是ID参数，需查询并记录日志
 
 4rbps完整版含自动登录.zip为未改动过的源代码
 
          
