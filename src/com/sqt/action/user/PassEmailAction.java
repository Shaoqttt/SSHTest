package com.sqt.action.user;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.UUID;

import com.opensymphony.xwork2.ActionSupport;
import com.sqt.bean.User;
import com.sqt.service.UserService;
import com.sqt.utils.MD5;
import com.sqt.utils.Mail;

public class PassEmailAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private UserService userService;
	private String backsid;//接收验证sid
	private String backemail;//接收验证email
	private String lastName;
	private String lastName2;
	
	public String sendEmail() throws ParseException{
		System.out.println("-------PassEmailAction.sendEmail-----------" + user.getEmail());
		user=this.userService.findUserByEmail(user.getEmail());
		if (user!=null) {
			System.out.println("准备发送邮件");
			Mail mail=new Mail();
			String secretKey = UUID.randomUUID().toString(); // 密钥
			Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
			Long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数  mySql 取出时间是忽略毫秒数的
			this.userService.updateValidate(secretKey, outDate+"", user.getEmail());
			
			String key =user.getFirstName() + "$" + date + "$" + secretKey;
			System.out.println(" key>>>"+key);
			String digitalSignature = MD5.string2MD5(key);// 数字签名
			
             String basePath = "http://localhost:8080/ssh2/";
             String resetPassHref = basePath + "passEmail!checkResetLink?backsid="
                     + digitalSignature +"&backemail="+user.getEmail();
             String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="
                     + resetPassHref + " target='_BLANK'>" + resetPassHref
                     + "</a>  或者    <a href=" + resetPassHref
                     + " target='_BLANK'>点击我重新设置密码</a>"
                     + "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'" ;
             System.out.println("emailContent:"+emailContent);
             mail.setTo(user.getEmail());
             mail.setFrom("shaoqianting@163.com");// 你的邮箱
             mail.setHost("smtp.163.com");
             mail.setUsername("shaoqianting@163.com");// 用户
             mail.setPassword("123456...z");// 密码
             mail.setSubject("找回您的账户密码");
             mail.setContent(emailContent);
             if (mail.sendMail()) {
                 System.out.println(" 发送成功");
                 addActionError("重置密码邮件已经发送，请登陆邮箱进行重置！");
                 return SUCCESS;
             }
             else {
            	 addActionError("用户名不存在,你不会忘记邮箱了吧?");
            	 return "false";
			}
		}
		else{
			addActionError("用户名不存在,你不会忘记邮箱了吧?");
			return "false";
		}
		
	}
	public String checkResetLink() throws ParseException {
		System.out.println("sid>>>" + backsid);
		if (backsid.equals("")  || backemail.equals("")) {
			addActionError("链接不完整,请重新生成");
            System.out.println(">>>>> null");
            return "false";
        }
		user=this.userService.findUserByEmail(backemail);
		
		Timestamp outDate  = Timestamp.valueOf(user.getOutDate()); 
		System.out.println("now >>>"+System.currentTimeMillis()+"outDate >>>"+outDate.getTime());
		 if(outDate.getTime() <= System.currentTimeMillis()){ //表示已经过期
			 addActionError("链接已经过期,请重新申请找回密码.");
             System.out.println("时间 超时");
             return "false";
         }
		 String key = user.getFirstName()+"$"+outDate.getTime()/1000*1000+"$"+user.getValidataCode();//数字签名
		 System.out.println("key link》》"+key);
		 String digitalSignature = MD5.string2MD5(key);// 数字签名

		 if(!digitalSignature.equals(backsid)) {
			   addActionError("链接不正确,是否已经过期了?重新申请吧.");
               System.out.println("标示不正确");
               return "false";
         }else {
           //链接验证通过 转到修改密码页面
           return "changepass";
       }
	}
	public String updatePass(){
		System.out.println("-------UserAction.UpdateUser-----------" + user.getFirstName()+" lastName:"+lastName+" lastName2:"+lastName2);
		if (!(lastName.equals(lastName2))) {
			System.out.println("不相同");
			addActionError("密码修改失败！");
			return "false";
		}
		boolean b=this.userService.updatePsss(user.getFirstName(), lastName);
		if (b) {
			System.out.println("修改成功");
			addActionError("密码修改成功！");
			return "success";
		}else{
			System.out.println("修改失败");
			addActionError("密码修改失败！");
			return "false";
		}
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
	public String getBacksid() {
		return backsid;
	}
	public void setBacksid(String backsid) {
		this.backsid = backsid;
	}
	public String getBackemail() {
		return backemail;
	}
	public void setBackemail(String backemail) {
		this.backemail = backemail;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName2() {
		return lastName2;
	}
	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}
	
}
