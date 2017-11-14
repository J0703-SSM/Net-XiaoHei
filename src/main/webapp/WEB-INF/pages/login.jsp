<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link type="text/css" rel="stylesheet" media="all" href="../../styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="../../styles/global_color.css"/>
    <%--<link href="../../BS/css/bootstrap.min.css" rel="stylesheet"/>--%>
    <script src="../../BS/js/jquery-3.2.1.js"></script>

    <style>

        .passCode{

            font-size:10pt;
            color: greenyellow;

        }

    </style>
</head>
<body class="index">
<div class="login_box">
    <form action="/universal/login" method="post">
        <table>
            <tr>
                <td class="login_info">账号：</td>
                <td colspan="2"><input placeholder="账号或邮箱" name="username" type="text" class="width150" id="username" value="${username}"/></td>
                <td id="userMsg"></td>
            </tr>
            <tr>
                <td class="login_info">密码：</td>
                <td colspan="2"><input name="password" type="password" class="width150"/></td>
                <td><span class="required" id="passwordError">${passwordError}</span></td>
            </tr>
            <tr>
                <td class="login_info">验证码：</td>
                <td class="width70"><input id="codeText" name="verfCode" type="text" class="width70"/></td>
                <td><img src="/universal/codeImg" alt="验证码" title="点击更换" id="verifyCodeImage"/></td>
                <td id="codeMsg"><span class="required">${codeText}</span></td>
            </tr>
            <tr>
                <td></td>
                <td class="login_button" colspan="2">
                    <input type="image" id="loginButton" src="../../images/login_btn.png">
                </td>
                <td><span class="required"></span></td>
            </tr>
        </table>
    </form>
</div>
</body>

<script>

    /* 提交前判断 */
    $("#loginButton").click(function () {

//        alert("提交表单事件");

        if($("#codeId").text() != "验证码通过"){

            return false;

        }

//        if($("#nameId").text() != "用户正确"){
//
//            return false;
//
//        }

    });


    <%--点击更新验证--%>
    $("#verifyCodeImage").click(function () {

        $("#verifyCodeImage").attr('src', '/universal/codeImg?a='+Math.random())

    });

    /* ajax验证码 */
    $("#codeText").blur(function () {

        $("#passwordError").empty();

        $.post("/universal/passCode",{codeText:$("#codeText").val()},function (date) {

            if(date['codeTrue'] != null){

                $("#codeMsg").html("<span class='passCode' id='codeId'>"+date['codeTrue']+"</span> ");

            }

            if(date['codeFalse'] != null){

                $("#codeMsg").html("<span class='required' id='codeId'>"+date['codeFalse']+"</span> ");

            }

        })

    })

    /* ajax用户名 */
    $("#username").blur(function () {

        $.post("/universal/passUsername",{username:$("#username").val()},function (date) {

            if(date['userTrue'] != null){

                $("#userMsg").html("<span class='passCode' id='nameId'>"+date['userTrue']+"</span> ");

            }

            if(date['userFalse'] != null){

                $("#userMsg").html("<span class='required' id='nameId'>"+date['userFalse']+"</span> ");

            }

        })

    })

</script>
</html>
