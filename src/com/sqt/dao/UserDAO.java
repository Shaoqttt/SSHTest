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
    public boolean loginUser(String firstname,String lastname);//判断登录
    
    public User findUserByEmail(String email) throws ParseException ;
    public void updateValidate(String validataCode, String outDate, String email);
    public boolean updatePass(String firstName,String lastName);
    
    /**   
	 * 分页查询   
	 * @param hql  查询条件   
	 * @param offset  开始记录   
	 * @param length  一次查询几条记录   
	 * @return 查询的记录集合   
	 */    
	public List<User> queryForPage(final int offset,final int length,String hql);
	
	/**   
	 * 查询所有的记录数   
	 * @param hql 查询条件   
	 * @return 总记录数   
	 */    
	public int getAllRowCount(String hql);
	
}  