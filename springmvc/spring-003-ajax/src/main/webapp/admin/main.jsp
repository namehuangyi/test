<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2024/4/15
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>main.......</h2>
<h1>学生集合</h1>
<table width="50%" border="1px">
    <tr>
        <th>姓名</th>
        <th>生日</th>
    </tr>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.birth}------- <fmt:formatDate value="${user.birth}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
