<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'list.jsp' starting page</title>
<script type="text/javascript">
	function del() {
		if (confirm("确认删除?")) {
			return true;
		}
		return false;
	}
</script>
</head>

<body>
	<h1>
		<font color="red">Users List</font>
	</h1>
	<s:a href="/ssh2/index.jsp">返回</s:a>
	<table border="1" width="80%" align="center">
		<tr>
			<th>序号</th>
			<th>姓</th>
			<th>名</th>
			<th>年龄</th>
			<th>删除</th>
			<th>更新</th>
		</tr>
		<s:iterator value="#request.list" id="us">
			<tr>
				<td><s:property value="#us.id" /></td>
				<td><s:property value="#us.firstName" /></td>
				<td><s:property value="#us.lastName" /></td>
				<td><s:property value="#us.age" /></td>
				<td align="center">
					<s:a href="deleteUser_success.action?user.id=%{#us.id}" onclick="return del()">Delete</s:a>
				</td>
				<td align="center">
					<s:a href="updatePUser_success.action?user.id=%{#us.id}">Update</s:a>
				</td>
			</tr>
		</s:iterator>
		
		<s:iterator value="pageBean" >
			<tr>
				<td colspan="6" align="center" bgcolor="#5BA8DE">
					共<s:property value="allRow" />条记录
					共<s:property value="totalPage" />页
					当前第<s:property value="currentPage" />页<br> 
					<s:if test="%{currentPage == 1}">    
           				第一页  上一页
			        </s:if> <!-- currentPage为当前页 -->
			        <s:else>
						<a href="userMainAction!listUser.action?page=1">第一页</a>
						<a href="userMainAction!listUser.action?page=<s:property value="%{currentPage-1}"/>">上一页</a>
					</s:else>
					<s:if test="%{currentPage != totalPage}">
						<a href="userMainAction!listUser.action?page=<s:property value="%{currentPage+1}"/>">下一页</a>
						<a href="userMainAction!listUser.action?page=<s:property value="totalPage"/>">最后一页</a>
					</s:if>
					<s:else>
						下一页  最后一页
			        </s:else>
				</td>
			</tr>
		</s:iterator>
		
		<s:select id="select" name="user" list="#request.list" listValue="firstName"  listKey="id" label="name" headerKey="0" >
		
		</s:select> <span>所在位置</span>

	</table>
	<script>
		funtion(){
			var text="请选择";
			var select=document.getElementById("select");
			select.setAttribute("headerValue",text);
		}
		
	</script>
</body>
</html>
