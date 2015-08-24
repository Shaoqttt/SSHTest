package com.sqt.dao.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.sqt.bean.User;
import com.sqt.dao.UserDAO;


public class UserDAOImpl  implements UserDAO {
	public SessionFactory sessionFactory; 

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void doCreateUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("-------UserDaoImp.doCreateUser-----------"+user.getFirstName());
		System.out.println("id"+user.getId());
		System.out.println("firstname"+user.getFirstName());
		System.out.println("lastname"+user.getLastName());
		System.out.println("age"+user.getAge());
		getSession().save(user);
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		String hql = "from User u order by u.id asc";
		Query query=getSession().createQuery(hql);
		List<User> list =query.list();
		return list;
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("-------UserDaoImp.removeUser-----------"+user.getFirstName());
		getSession().delete(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("-------UserDaoImp.updateUser-----------"+user.getFirstName());
		getSession().update(user);
	}

	@Override
	public List<User> findUserById(int id) {
		// TODO Auto-generated method stub
		System.out.println("-------UserDaoImp.findUserById-----------"+id);
		String hql = "from User u where u.id=:id order by u.id asc";
		Query query=getSession().createQuery(hql);
		query.setInteger("id", id);
		List<User> list =query.list();
		return list;
	}
	@Override
	public User UpUserById(int id) {
		// TODO Auto-generated method stub
		System.out.println("-------UserDaoImp.UpUserById-----------"+id);
		User user = null;
		user = (User) getSession().get(User.class, id);
		
		return user;
	}

	@Override
	public boolean loginUser(String firstname,String lastname) {
		// TODO Auto-generated method stub
		System.out.println("-------UserDaoImp.loginUser-----------"+firstname);
		String hql="from User u where u.firstName=:firstName and u.lastName=:lastName";
		
		Query query=getSession().createQuery(hql);
		query.setString("firstName", firstname);
		query.setString("lastName", lastname);
		List<User> list=query.list();
		if (list.size()==1) {
			return true;
		}
		else if (list.size()==0) {
			return false;
		}
		return false;
	}

	@Override
	public User findUserByEmail(String email) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println("-------UserDaoImp.findUserByEmail-----------"+email);
		String hql="from User u where u.email=:email";
		Query query=getSession().createQuery(hql);
		query.setString("email", email);
		List<User> list=query.list();
		
		User user = new User();
		if (list.size()==0) {
			user=null;
			return user;
		}
		System.out.println("list:"+list.get(0).getFirstName());
		user.setId(list.get(0).getId());
		user.setFirstName(list.get(0).getFirstName());
		user.setLastName(list.get(0).getLastName());
		user.setAge(list.get(0).getAge());
		if (list.get(0).getEmail()!=null||list.get(0).getEmail()=="") {
			user.setEmail(list.get(0).getEmail());
		}
		if (list.get(0).getValidataCode()!=null||list.get(0).getValidataCode()=="") {
			user.setValidataCode(list.get(0).getValidataCode());
		}
		if (list.get(0).getOutDate()!=null||list.get(0).getOutDate()=="") {
			user.setOutDate(list.get(0).getOutDate());
		}
		return user;
	}

	@Override
	public void updateValidate(String validataCode, String outDate, String email) {
		// TODO Auto-generated method stub
		System.out.println("-------UserDaoImp.updateValidate-----------");
		String hql="update User u set u.validataCode=:validataCode ,u.outDate=:outDate where u.email=:email";
		Query query = getSession().createQuery(hql);
		query.setString("validataCode", validataCode);
		query.setString("outDate", outDate);
		query.setString("email", email);
		query.executeUpdate();
	}

	@Override
	public boolean updatePass(String firstName, String lastName) {
		// TODO Auto-generated method stub
		System.out.println("-------UserDaoImp.updatePass-----------");
		String hql="update User u set u.lastName=:lastName where u.firstName=:firstName";
		Query query = getSession().createQuery(hql);
		query.setString("lastName", lastName);
		query.setString("firstName", firstName);
		int b = query.executeUpdate();
		if (b==1) {
			return true;
		}
		else {
			return false;
		}
	}

	/**   
	 * 分页查询   
	 * @param hql  查询条件   
	 * @param offset  开始记录   
	 * @param length  一次查询几条记录   
	 * @return 查询的记录集合   
	 */    
	@Override
	public List<User> queryForPage(int offset, int length,String hql) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		List<User> list=query.list();
		System.out.println("listsize:"+list.size());
		return list;
	}

	@Override
	public int getAllRowCount(String hql) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		return query.list().size();
	}
	
}