package com.sqt.action.user;

import com.sqt.bean.User;
import com.sqt.service.UserService;
import com.sqt.utils.CookieUtils;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginUserAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private CookieUtils cookieUtils = new CookieUtils();
	private User user = new User();
	private UserService userService;
	private String checkCode;//����ҳ�л�ȡ��
	private boolean saveCookie;//�ж��Ƿ���Ҫ����cookie
	
	public String getCheckCode()
    {
        return checkCode;
    }

    public void setCheckCode(String checkCode)
    {
        this.checkCode = checkCode;
    }

	public boolean getSaveCookie() {
		return saveCookie;
	}

	public void setSaveCookie(boolean saveCookie) {
		this.saveCookie = saveCookie;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	// �û���¼��ת
	public String login() {
		if (cookieUtils.getCookie(request, userService, user)) {
			return SUCCESS;
		} else
			return "login";
	}

	@Override
	public String execute() throws Exception {
		// ����service��صķ��������ʵ�ʵ�ҵ����
		Map session = ActionContext.getContext().getSession();
		System.out.println("-------UserAction.LoginUser-----------" + user.getFirstName());
		System.out.println("saveCookie:" + saveCookie);
		boolean b = this.userService.login(user.getFirstName(),user.getLastName());
		String checkCode2 = (String)session.get("checkCode");
		System.out.println("checkCode:"+checkCode);
		if (b&&checkCode2.equalsIgnoreCase(checkCode)) {
			if (saveCookie) {
				Cookie cookie = cookieUtils.addCookie(user);
				response.addCookie(cookie);// ���cookie��response��
			}
			
			session.put("user.firstName", user.getFirstName());
			return SUCCESS;
		} else {
			System.out.println("b"+b);
			if (!b) {
				addActionError("�˺��������");
			}else{
				addActionError("��֤�����");
			}
			return "fales";
		}
	}

	public String loginOut() {
		Map session = ActionContext.getContext().getSession();
		System.out.println("-------UserAction.LoginOutUser-----------" + session.get("user.firstName"));
		 
        if (session != null)  
        	session.remove("user.firstName");
        //Cookie cookie = cookieUtils.delCookie(request);  
//        if (cookie != null)  
//            response.addCookie(cookie); 
		return "login";

	}

}