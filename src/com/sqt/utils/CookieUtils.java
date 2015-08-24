package com.sqt.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.sqt.bean.User;
import com.sqt.service.UserService;

public class CookieUtils {
	public static final String USER_COOKIE = "user.cookie";
	// 添加一个cookie
	public Cookie addCookie(User user) {
		Cookie cookie = new Cookie(USER_COOKIE, user.getFirstName() + "," + user.getLastName());
		System.out.println("---添加cookie---"+cookie.getName());
		cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周60 * 60 * 24 * 14
		return cookie;
	}
	
	// 删除cookie  
    public Cookie delCookie(HttpServletRequest request) {  
        Cookie[] cookies = request.getCookies();  
        if (cookies != null) {
            for (Cookie cookie : cookies) {  
                if (USER_COOKIE.equals(cookie.getName())) {  
                    cookie.setValue("");  
                    cookie.setMaxAge(0);  
                    return cookie;  
                }  
            }  
        }  
        return null;  
    }  
    // 得到cookie  
    public  boolean getCookie(HttpServletRequest request, UserService userService,User user) {  
        Cookie[] cookies = request.getCookies();
        System.out.println("cookies: " + cookies);
        if (cookies != null) {  
            for (Cookie cookie : cookies) {  
                System.out.println("cookie: " + cookie.getName());  
                if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {  
                    String value = cookie.getValue();  
                    if (StringUtils.isNotBlank(value)) {  
                        String[] split = value.split(",");  
                        String username = split[0];  
                        String password = split[1];
                        boolean b=userService.login(username,password);
                        if (b) {  
                            HttpSession session = request.getSession();
                            session.setAttribute("user.firstName", user.getFirstName());// 添加用户到session中  
                            return true;  
                        } 
                    }  
                }  
            }  
        }  
        return false;  
    }  
}
