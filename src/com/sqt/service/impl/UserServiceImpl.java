package com.sqt.service.impl;

import java.text.ParseException;
import java.util.List;

import com.sqt.bean.Page;
import com.sqt.bean.User;
import com.sqt.dao.UserDAO;
import com.sqt.dao.impl.UserDAOImpl;
import com.sqt.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO dao;

	public UserDAO getDao() {
		return dao;
	}

	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	public void doCreateUser(User user) {
		System.out.println("-------UserService.doCreateUser-----------" + user.getFirstName());
		this.dao.doCreateUser(user);
	}

	public void delete(User user) {
		System.out.println("-------UserService.deleteUser-----------" + user.getFirstName());
		this.dao.removeUser(user);
	}

	public void update(User user) {
		System.out.println("-------UserService.updateUser-----------" + user.getFirstName());
		this.dao.updateUser(user);
	}

	public List<User> findAllUsers() {
		System.out.println("-------UserService.findAllUsers-----------");
		return this.dao.findAllUsers();
	}

	public List<User> findUserById(int id) {
		System.out.println("-------UserService.findUserById-----------" + id);
		return this.dao.findUserById(id);
	}

	public User UpUserById(int id) {
		// TODO Auto-generated method stub
		System.out.println("-------UserService.UpUserById-----------" + id);
		return this.dao.UpUserById(id);
	}

	@Override
	public boolean login(String firstname, String lastname) {
		// TODO Auto-generated method stub
		System.out.println("-------UserService.login-----------" + firstname);
		return this.dao.loginUser(firstname, lastname);
	}

	@Override
	public User findUserByEmail(String email) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println("-------UserService.findUserByEmail-----------" + email);
		return this.dao.findUserByEmail(email);
	}

	@Override
	public void updateValidate(String validataCode, String outDate, String email) {
		// TODO Auto-generated method stub
		System.out.println("-------UserService.updateValidate-----------" + email);
		this.dao.updateValidate(validataCode, outDate, email);
		System.out.println("update success");
	}

	@Override
	public boolean updatePsss(String firstName, String lastName) {
		// TODO Auto-generated method stub
		System.out.println("-------UserService.updatePsss-----------" + firstName);
		return this.dao.updatePass(firstName, lastName);
	}

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param pageSize
	 *            ÿҳ��ʾ���ټ�¼
	 * @param currentPage
	 *            ��ǰҳ
	 * @return ��װ�˷�ҳ��Ϣ��bean
	 */
	@Override
	public Page queryForPage(int pageSize, int page) {
		// TODO Auto-generated method stub
		String hql="from User user order by user.id";
		final int offset = Page.countOffset(pageSize, page); // ��ǰҳ��ʼ��¼
		final int length = pageSize; // ÿҳ��¼��
		int allRow = dao.getAllRowCount(hql); // �ܼ�¼��
		int totalPage = Page.countTatalPage(pageSize, allRow); // ��ҳ��
		final int currentPage = Page.countCurrentPage(page); // ��ǰҳ

		List list = dao.queryForPage(offset, length, hql);
		// �ѷ�ҳ��Ϣ���浽Bean����
		Page pageBean = new Page();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	@Override
	public Page queryForPageById(int pageSize, int page, int id) {
		// TODO Auto-generated method stub
		String hql="from User user where user.id="+id+" order by user.id";
		final int offset = Page.countOffset(pageSize, page); // ��ǰҳ��ʼ��¼
		final int length = pageSize; // ÿҳ��¼��
		int allRow = dao.getAllRowCount(hql); // �ܼ�¼��
		int totalPage = Page.countTatalPage(pageSize, allRow); // ��ҳ��
		final int currentPage = Page.countCurrentPage(page); // ��ǰҳ

		List list = dao.queryForPage(offset, length, hql);
		// �ѷ�ҳ��Ϣ���浽Bean����
		Page pageBean = new Page();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
}