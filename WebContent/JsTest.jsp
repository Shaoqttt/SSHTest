<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>

<body>
<center>
<script>
	document.write("<h1>This is a heading</h1>");
	document.write("<p>This is a paragraph.</p>");
	
	function myFunction()
	{
		document.getElementById("demo").innerHTML="My First JavaScript Function";
	}
	function myFunction2()
	{
		document.write("糟糕！文档消失了。");
	}
	</script>
	
	<p id="demo">A Paragraph</p>
	
	<button type="button" onclick="myFunction2()">Try it</button>
	
	<div id="box">
		<div id="username"><input type="text" value="请输入账号"></div>
		<div id="password"><input type="text" value="请输入密码"></div>
		<input type="button" value="提交">
	</div>
</center>
</body>

</html>