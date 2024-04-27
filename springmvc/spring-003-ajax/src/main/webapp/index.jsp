<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2024/4/14
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function showStu(){
        $.ajax({
            url : "${pageContext.request.contextPath}/list.action",
            type : "get",
            dataType : "json",
            success : function (stuList){
                var s = "";
                $.each(stuList, function (i, stu){
                    s += stu.name + "-----" + stu.age + "<br>";
                });
                $("#mydiv").html(s);
            }
        });
    }
</script>
<a href="javascript:showStu()">访问服务器返回学生集合</a><br>
<div id="mydiv">等待服务器返回数据</div>

<form action="${pageContext.request.contextPath}/date.action">
    <input type="date" name="mydate">
    <input type="submit", value="提交">
</form>

<a href="${pageContext.request.contextPath}/date.action">显示集合中的日期成员</a>
</body>
</html>
