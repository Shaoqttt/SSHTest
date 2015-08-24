package com.sqt.action.user;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sqt.bean.Page;
import com.sqt.bean.User;
import com.sqt.service.UserService;

public class userMainAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Page pageBean;// 封装了分页信息和数据内容的pageBean
	private List<User> listUser;// 用于储存pageBean当中被封装的User信息
	private int page = 1; // 表示从网页中返回的当前页的值 默认为1 表示默认显示第一页内容
	private UserService userService;
	private String lastName;
	private int i;
	// delete
	public String deleteUser() throws Exception {
		System.out.println("-------UserAction.deleteUser-----------" + user.getFirstName());
		userService.delete(user);
		return "listUser";
	}

	// save
	public String saveUser() throws Exception {
		System.out.println("-------UserAction.SaveUser-----------" + user.getFirstName());
		userService.doCreateUser(user);
		return "listUser";
	}

	// update 执行更新
	public String updateUser() throws Exception {
		System.out.println("-------UserAction.UpdateUser-----------" + user.getFirstName());
		userService.update(user);
		return "listUser";
	}

	// updateP 获取需要更新的数据
	public String updatePUser() throws Exception {
		System.out.println("-------UserAction.UpdatePUser-----------" + user.getId());
		user = this.userService.UpUserById(user.getId());
		return "updateuser";
	}

	// find 通过id查找
	public String findUser() throws Exception {
		System.out.println("-------UserAction.pageListUser-----------");
		String searchType="";
		this.pageBean = userService.queryForPageById(5, page,user.getId());//获取封装了分页信息和数据的pageBean    
		this.listUser = this.pageBean.getList(); //获取数据
		
		System.out.println("-------UserAction.FindUser-----------" + user.getId());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("list", this.listUser);
		return "listuser";
	}

	// userlist 用户的列表
	public String listUser() throws Exception {
		System.out.println("-------UserAction.pageListUser-----------");
		String searchType="searchAllUser";
		this.pageBean = userService.queryForPage(5, page);//获取封装了分页信息和数据的pageBean    
		this.listUser = this.pageBean.getList(); //获取数据    
		
		System.out.println("-------UserAction.ListUser-----------");
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("list", this.listUser);
		return "listuser";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Page getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page pageBean) {
		this.pageBean = pageBean;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
