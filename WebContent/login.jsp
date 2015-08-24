<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
     function refresh(obj){
         obj.src="createImageAction.action?id="+Math.random();
     }
</script>
<body>
<h1><font color="red">Login</font></h1>
		
	<s:form action="loginUser">
		<s:textfield name="user.firstName" label="First Name"></s:textfield>
		<s:textfield name="user.lastName" label="Last Name"></s:textfield>
		<s:textfield name="checkCode" label="验证码:"></s:textfield>
		<s:checkbox name="saveCookie" label="下次自动登录" value="true" />
        <img src="createImageAction" onclick="refresh(this)" title="点击图片刷新验证码"/><br>
        <s:actionerror cssStyle="color:red"/>
		
		<s:submit></s:submit>
	</s:form>
</body>
</html>