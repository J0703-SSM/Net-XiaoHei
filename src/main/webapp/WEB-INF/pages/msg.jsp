<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>邮件确认</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <%--<meta http-equiv="refresh" content="5;url=jsps/body.jsp">--%>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link href="../../BS/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../BS/js/jquery-3.2.1.js"></script>
    <script src="../../BS/js/bootstrap.min.js"></script>

</head>
<body onload="showMun()">


<div class="jumbotron text-center">
    <h3>${msg}</h3>
    <p id="time"></p>
    <p><a class="btn btn-primary btn-lg" href="about:blank" role="button">立即跳转</a></p>
</div>

<%--<ul>--%>
<%--<li><a href="<c:url value='/index.jsp'/>">主界面</a></li>--%>
<%--<li><a href="<c:url value='/jsps/user/login.jsp'/>">登录</a></li>--%>
<%--<li><a href="<c:url value='/jsps/user/regist.jsp'/>">注册</a></li>--%>
<%--</ul>--%>
</body>

<script>

    var i = 6;

    function showMun() {

        if(i == 0){

            window.location = "about:blank";

        }

        i = i - 1;

        document.getElementById("time").innerHTML = "还有" + i + "秒跳转页面";

        setTimeout('showMun()', 1000);

    }

</script>

</html>
