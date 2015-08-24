<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  <script type="text/javascript">
	//var myfrom=document.getElementById("myform1");

	
	function addUser()
	{
		var myform=document.forms[0];
		myform.action="user/addUser_success";
		myform.method="post";
		myform.submit();
	}
	
	function modifyUser()
	{
		var myform=document.forms[0];
		myform.action="user/modifyUser_success";
		myform.method="post";
		myform.submit();
	}
	
	function deleteUser()
	{
		var myform=document.forms[0];
		myform.action="user/deleteUser_success";
		myform.method="post";
		myform.submit();
	}
	
	function listUser()
	{
		var myform=document.forms[0];
		myform.action="user/listUser_success";
		myform.method="post";
		myform.submit();
	}
</script>
  <body>
	<%
		String username=(String)session.getAttribute("user.firstName");
	%>
 
	<h1><font color="red">Operation List</font></h1>
	<%=username %>&nbsp<s:a href="loginUser!loginOut.action">退出</s:a>
	<s:a href="save.jsp">Save User</s:a><br/>
	<s:a href="listUser_success.action">List Users</s:a>
	<s:form action="findUser_success.action">
		<s:textfield name="user.id" label="id"></s:textfield>
		<s:submit></s:submit>
	</s:form>
  </body>
</html>