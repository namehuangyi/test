<%@ page import="com.bjpowernode.oa.servlet.bean.Dept" %>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8'>
		<title>修改部门</title>
	</head>
	<body>
		<h1>修改部门</h1>
		<hr >
		<form action="${pageContext.request.contextPath}/dept/modify" method="post">
		部门编号：<input type='text' name='deptno' readonly value="${dept.deptno}" /><br />
		部门名称：<input type='text' name='dname' value="${dept.dname}" /><br />
		部门位置：<input type='text' name='loc' value="${dept.loc}" /><br />
		<button type='submit'>修改</button>
		</form>
	</body>
</html>