<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>My JSP 'update.jsp' starting page</title>

  </head>
  
  <body>
	<h1><font color="red">Update User</font></h1>
	
	<s:form action="updateUser_success.action">
	<table>
		<tr>
			<td>
				<s:hidden name="user.id" value="%{user.id}"></s:hidden>
			</td>
		</tr>
	
		<tr>
			<td>
				<s:textfield name="user.firstName" value="%{user.firstName}" label="First Name"></s:textfield>
			</td>
		</tr>
		
		<tr>
			<td>
				<s:textfield name="user.lastName" value="%{user.lastName}" label="Last Name"></s:textfield>
			</td>
		</tr>
		
		<tr>
			<td>
				<s:textfield name="user.age" value="%{user.age}" label="Age"></s:textfield>
			</td>
		</tr>
		
		<tr>
			<td>
				<s:submit></s:submit>
			</td>
		</tr>
	</table>	
	</s:form>
	
  </body>
</html>
