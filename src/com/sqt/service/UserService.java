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
	
	public User findUserByEmail(String email) throws ParseException;//ͨ����������û�
	public void updateValidate(String validataCode,String outDate,String email);//ͨ���������������֤
	public boolean updatePsss(String firstName,String lastName);
	
	/**   
	 * ��ҳ��ѯ     
	 * @param pageSize  ÿҳ��ʾ���ټ�¼   
	 * @param currentPage ��ǰҳ   
	 * @return ��װ�˷�ҳ��Ϣ��bean
	 */
	public Page queryForPage(int pageSize,int page);
	public Page queryForPageById(int pageSize,int page,int id);
}