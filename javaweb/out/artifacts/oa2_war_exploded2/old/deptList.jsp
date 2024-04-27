<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.oa.servlet.bean.Dept" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%--<%@page session="false" %>--%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门列表页面</title>
	</head>
	<body>
	<h3>欢迎<%=session.getAttribute("username")%></h3>
	<a href="<%=request.getContextPath()%>/user/exit">[退出登录]</a>

		<script type="text/javascript">
			function del(deptno){
				if(window.confirm("亲，删了不可恢复哦！")){
					document.location.href= "<%=request.getContextPath()%>/dept/del?deptno=" + deptno;
				}
			}
		</script>
	
		<h1 align="center">部门列表</h1>
		<hr />
		<table border="1px" align="center" width="50%">
			<tr>
				<th>序号</th>
				<th>部门编号</th>
				<th>部门名称</th>
				<th>操作</th>
			</tr>

			<%
				List<Dept> deptList = (List<Dept>)request.getAttribute("deptList");
				int i = 0;
				for (Dept dept : deptList){
			%>
			<tr>
				<td><%=++i%></td>
				<td><%=dept.getDeptno()%></td>
				<td><%=dept.getDname()%></td>
				<td>
					<a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除</a>
					<a href="<%=request.getContextPath()%>/dept/select?f=edit&deptno=<%=dept.getDeptno()%>">修改</a>
					<a href="<%=request.getContextPath()%>/dept/select?f=select&deptno=<%=dept.getDeptno()%>">详情</a>
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<hr />
		<a href="<%=request.getContextPath()%>/old/add.jsp.jsp">新增部门</a>
	</body>
</html>