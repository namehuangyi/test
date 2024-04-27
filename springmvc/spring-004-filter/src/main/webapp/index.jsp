
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login page</title>
    <script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>登录页面</h1>
<form action="${pageContext.request.contextPath}/showLogin">
    <input type="text" name="name"><br>
    <input type="text" name="password"><br>
    <input type="submit" value="登录"><br>
</form>
</body>
</html>
