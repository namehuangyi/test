<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门详细信息</title>
	</head>
	<body>
		<h1>部门详情</h1>
		<hr >
		部门编号：${dept.deptno}<br>
		部门名称：${dept.dname}<br>
		部门位置：${dept.loc}<br>
		<button onclick="window.history.back()">后退</button>
	</body>
</html>