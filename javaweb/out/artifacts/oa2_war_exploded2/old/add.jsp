<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>新增页面</title>
	</head>
	<body>
		<form action="<%=request.getContextPath()%>/dept/add" method="post">
			部门编号：<input type="text" name="deptno" /><br />
			部门名称：<input type="text" name="dname" /><br />
			部门位置：<input type="text" name="loc" /><br />
			<button type="submit">保存</button>
		</form>
	</body>
</html>