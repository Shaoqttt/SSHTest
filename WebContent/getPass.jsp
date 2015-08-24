<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>找回密码</title>
</head>
<body>
	<s:form action="passEmail!sendEmail.action">
		<s:textfield name="user.email" label="用户邮箱"></s:textfield>
		<br>
		<s:actionerror cssStyle="color:red" />

		<s:submit value="发送邮件"></s:submit>
	</s:form>
</body>
</html>