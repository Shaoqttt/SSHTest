package com.sqt.interceptor;


import java.util.Map;

import javax.servlet.http.Cookie;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sqt.action.user.LoginUserAction;
import com.sqt.action.user.userMainAction;
import com.sqt.service.UserService;
import com.sqt.service.impl.UserServiceImpl;


public class CheckLogin implements Interceptor {
	private UserService userService=new UserServiceImpl();

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void destroy() {
		System.out.println("------CheckLogin.destroy------");
	}

	@Override
	public void init() {
		System.out.println("------CheckLogin.init------");

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {

		System.out.println("------CheckLogin.intercept------");
		Object action = arg0.getAction(); // 获取当前的action
		Map session = ActionContext.getContext().getSession();
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		System.out.println("firstName:" + session.get("user.firstName"));
		if (!(action instanceof LoginUserAction)) 
		{
			if (session.get("user.firstName") != null)
			{
				return arg0.invoke();
			}
			else 
			{
				if (cookies != null) 
				{
					for (Cookie c : cookies)
					{
						
						if (c.getName().equalsIgnoreCase("user.cookie")) {
							String value = c.getValue();
							System.out.println("进入cookie判断" + c.getValue());
							if (StringUtils.isNotBlank(value)) {
								String[] split = value.split(",");
								String username = split[0];
								String password = split[1];
								System.out.println("username:" + username+"password:"+password);
								
								boolean b = this.userService.login(username,password);
								if (b) {
									System.out.println(b);
									session.put("user.firstName", username);
									return arg0.invoke();
								}
							}
						}
					}
				}
			}
			return "checkLoginFail";
		}
		return "checkLoginFail";
	}
}