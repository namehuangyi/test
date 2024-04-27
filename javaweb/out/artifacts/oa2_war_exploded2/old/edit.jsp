<%@ page import="com.bjpowernode.oa.servlet.bean.Dept" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%
	Dept dept = (Dept) request.getAttribute("dept");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8'>
		<title>修改页面</title>
	</head>
	<body>
		<h1>修改部门</h1>
		<hr >
		<form action="<%=request.getContextPath()%>/dept/modify" method="post">
		部门编号：<input type='text' name='deptno' readonly value="<%=dept.getDeptno()%>" /><br />
		部门名称：<input type='text' name='dname' value="<%=dept.getDname()%>" /><br />
		部门位置：<input type='text' name='loc' value="<%=dept.getLoc()%>" /><br />
		<button type='submit'>修改</button>
	</body>
</html>