package com.sqt.service;

import java.text.ParseException;
import java.util.List;

import com.sqt.bean.Page;
import com.sqt.bean.User;

public interface UserService {
	public void doCreateUser(User user);
	public List<User> findAllUsers();
	public void delete(User user);
	public void update(User user);
	public List<User> findUserById(int id);
	public User UpUserById(int id);
	public boolean login(String firstname,String lastname);
	
	public User findUserByEmail(String email) throws ParseException;//通过邮箱查找用户
	public void updateValidate(String validataCode,String outDate,String email);//通过邮箱更换密码验证
	public boolean updatePsss(String firstName,String lastName);
	
	/**   
	 * 分页查询     
	 * @param pageSize  每页显示多少记录   
	 * @param currentPage 当前页   
	 * @return 封装了分页信息的bean
	 */
	public Page queryForPage(int pageSize,int page);
	public Page queryForPageById(int pageSize,int page,int id);
}