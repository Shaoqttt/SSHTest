<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>

  </head>
  
  <body>
	<h1><font color="red">Save User</font></h1>
		
	<s:form action="saveUser">
		<s:textfield name="user.firstName" label="First Name"></s:textfield>
		<s:textfield name="user.lastName" label="Last Name"></s:textfield>
		<s:textfield name="user.age" label="Age"></s:textfield>
		<s:submit></s:submit>
	</s:form>
	
  </body>
</html>
