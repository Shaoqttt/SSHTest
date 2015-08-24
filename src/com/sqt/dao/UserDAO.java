package com.sqt.dao;

import java.text.ParseException;
import java.util.List;

import com.sqt.bean.User;

import sun.reflect.generics.tree.VoidDescriptor;

public interface UserDAO {  
    public void doCreateUser(User user);  
    public List<User> findAllUsers();  
    public void removeUser(User user);  
    public void updateUser(User user);  
    public List<User> findUserById(int id);
    public User UpUserById(int id);
    public boolean loginUser(String firstname,String lastname);//�жϵ�¼
    
    public User findUserByEmail(String email) throws ParseException ;
    public void updateValidate(String validataCode, String outDate, String email);
    public boolean updatePass(String firstName,String lastName);
    
    /**   
	 * ��ҳ��ѯ   
	 * @param hql  ��ѯ����   
	 * @param offset  ��ʼ��¼   
	 * @param length  һ�β�ѯ������¼   
	 * @return ��ѯ�ļ�¼����   
	 */    
	public List<User> queryForPage(final int offset,final int length,String hql);
	
	/**   
	 * ��ѯ���еļ�¼��   
	 * @param hql ��ѯ����   
	 * @return �ܼ�¼��   
	 */    
	public int getAllRowCount(String hql);
	
}  