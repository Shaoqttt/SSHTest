<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>更改密码</title>
</head>
<script type="text/javascript">
	function check() {
		var pw1 = document.getElementsByName("lastName").value;
		var pw2 = document.getElementsByName("lastName2").value;
		if(pw1==pw2)
		{
			alert("两次密码一样")
			
		}
		else
		{
			alert("两次密码不一样")
			
		}
	}
</script>
<body>
	<s:form action="passEmail!updatePass.action">
		<s:textfield name="user.firstName" label="用户名"></s:textfield>
		<br>
		<s:textfield name="lastName" label="请输入密码"></s:textfield>
		<br>
		<s:textfield name="lastName2" label="请再次密码"></s:textfield>
		<br>
		<s:actionerror cssStyle="color:red" />

		<s:submit value="修改" onclick="check()"></s:submit>
	</s:form>
</body>
</html>