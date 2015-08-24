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
	private String backsid;//������֤sid
	private String backemail;//������֤email
	private String lastName;
	private String lastName2;
	
	public String sendEmail() throws ParseException{
		System.out.println("-------PassEmailAction.sendEmail-----------" + user.getEmail());
		user=this.userService.findUserByEmail(user.getEmail());
		if (user!=null) {
			System.out.println("׼�������ʼ�");
			Mail mail=new Mail();
			String secretKey = UUID.randomUUID().toString(); // ��Կ
			Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30���Ӻ����
			Long date = outDate.getTime() / 1000 * 1000;// ���Ժ�����  mySql ȡ��ʱ���Ǻ��Ժ�������
			this.userService.updateValidate(secretKey, outDate+"", user.getEmail());
			
			String key =user.getFirstName() + "$" + date + "$" + secretKey;
			System.out.println(" key>>>"+key);
			String digitalSignature = MD5.string2MD5(key);// ����ǩ��
			
             String basePath = "http://localhost:8080/ssh2/";
             String resetPassHref = basePath + "passEmail!checkResetLink?backsid="
                     + digitalSignature +"&backemail="+user.getEmail();
             String emailContent = "����ظ����ʼ�.������������,��������<br/><a href="
                     + resetPassHref + " target='_BLANK'>" + resetPassHref
                     + "</a>  ����    <a href=" + resetPassHref
                     + " target='_BLANK'>�����������������</a>"
                     + "<br/>tips:���ʼ�����30����,���ӽ���ʧЧ����Ҫ��������'�һ�����'" ;
             System.out.println("emailContent:"+emailContent);
             mail.setTo(user.getEmail());
             mail.setFrom("shaoqianting@163.com");// �������
             mail.setHost("smtp.163.com");
             mail.setUsername("shaoqianting@163.com");// �û�
             mail.setPassword("123456...z");// ����
             mail.setSubject("�һ������˻�����");
             mail.setContent(emailContent);
             if (mail.sendMail()) {
                 System.out.println(" ���ͳɹ�");
                 addActionError("���������ʼ��Ѿ����ͣ����½����������ã�");
                 return SUCCESS;
             }
             else {
            	 addActionError("�û���������,�㲻�����������˰�?");
            	 return "false";
			}
		}
		else{
			addActionError("�û���������,�㲻�����������˰�?");
			return "false";
		}
		
	}
	public String checkResetLink() throws ParseException {
		System.out.println("sid>>>" + backsid);
		if (backsid.equals("")  || backemail.equals("")) {
			addActionError("���Ӳ�����,����������");
            System.out.println(">>>>> null");
            return "false";
        }
		user=this.userService.findUserByEmail(backemail);
		
		Timestamp outDate  = Timestamp.valueOf(user.getOutDate()); 
		System.out.println("now >>>"+System.currentTimeMillis()+"outDate >>>"+outDate.getTime());
		 if(outDate.getTime() <= System.currentTimeMillis()){ //��ʾ�Ѿ�����
			 addActionError("�����Ѿ�����,�����������һ�����.");
             System.out.println("ʱ�� ��ʱ");
             return "false";
         }
		 String key = user.getFirstName()+"$"+outDate.getTime()/1000*1000+"$"+user.getValidataCode();//����ǩ��
		 System.out.println("key link����"+key);
		 String digitalSignature = MD5.string2MD5(key);// ����ǩ��

		 if(!digitalSignature.equals(backsid)) {
			   addActionError("���Ӳ���ȷ,�Ƿ��Ѿ�������?���������.");
               System.out.println("��ʾ����ȷ");
               return "false";
         }else {
           //������֤ͨ�� ת���޸�����ҳ��
           return "changepass";
       }
	}
	public String updatePass(){
		System.out.println("-------UserAction.UpdateUser-----------" + user.getFirstName()+" lastName:"+lastName+" lastName2:"+lastName2);
		if (!(lastName.equals(lastName2))) {
			System.out.println("����ͬ");
			addActionError("�����޸�ʧ�ܣ�");
			return "false";
		}
		boolean b=this.userService.updatePsss(user.getFirstName(), lastName);
		if (b) {
			System.out.println("�޸ĳɹ�");
			addActionError("�����޸ĳɹ���");
			return "success";
		}else{
			System.out.println("�޸�ʧ��");
			addActionError("�����޸�ʧ�ܣ�");
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
