<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="../../../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../../../styles/global_color.css" />

        <script src="../../../BS/js/jquery-3.2.1.js"></script>

        <style>

            .passCode{

                /*height: 28px;*/
                line-height: 28px;
                float: right;
                overflow: hidden;
                text-align: left;
                padding-left:5px;
                width: 340px;
                color: limegreen;

            }

            .errorCode{

                /*height: 28px;*/
                line-height: 28px;
                float: right;
                overflow: hidden;
                text-align: left;
                padding-left:5px;
                width: 340px;
                color: red;

            }

        </style>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../../../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="/universal/intoIndex" class="index_off"></a></li>
                <li><a href="../role/role_list.jsp" class="role_off"></a></li>
                <li><a href="../admin/admin_list.jsp" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.jsp" class="fee_off"></a></li>
                <li><a href="../account/account_list.jsp" class="account_off"></a></li>
                <li><a href="../service/service_list.jsp" class="service_off"></a></li>
                <li><a href="../bill/bill_list.jsp" class="bill_off"></a></li>
                <li><a href="../report/report_list.jsp" class="report_off"></a></li>
                <li><a href="user_info.jsp" class="information_off"></a></li>
                <li><a href="user_modi_pwd.jsp" class="password_on"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <div id="main">      
            <!--保存操作后的提示信息：成功或者失败-->      
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，旧密码错误！-->
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>旧密码：</span></div>
                <div class="input_info">
                    <input type="password" id="oldPass" class="width200"  /><span class="required">*</span>
                    <span id="oldPassError"></span>
                </div>
                <div class="text_info clearfix"><span>新密码：</span></div>
                <div class="input_info">
                    <input type="password" id="newPassword"  class="width200" /><span class="required">*</span>
                    <span id="newPassError"></span>
                </div>
                <div class="text_info clearfix"><span>重复新密码：</span></div>
                <div class="input_info">
                    <input type="password" id="rePassword" class="width200"  /><span class="required">*</span>
                    <span id="rePassError"></span>
                    <%--<div class="validate_msg_medium">两次新密码必须相同</div>--%>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="updatePassword()" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>Powered By XiaoHei </p>
        </div>
    </body>

    <script>

        function oldPass() {

            if($("#oldPass").val() == ""){

                return false;

            }

            return true;
        }

        function newPass() {

            if($("#newPassword").val() == ""){

                return false;

            }

            return true;

        }

        function rePass() {

            if($("#rePassword").val() == ""){

                return false;

            }

            return true;

        }

        function rePassCompared() {

            if($("#rePassword").val() != $("#newPassword").val()){

                return false;

            }

            return true;

        }

        $("#oldPass").blur(function () {

            if(!oldPass()){

                $("#oldPassError").html("<div class='errorCode'>密码不能为空</div>");

            }else {

                $("#oldPassError").empty();

            }

        })

        $("#newPassword").blur(function () {

//            alert("jiru")

            if(!newPass()){

                $("#newPassError").html("<div class='errorCode'>新密码不能为空</div>");

            } else {

                $("#newPassError").html("<div class='passCode'>就这是这样!</div>");

            }
        })

        $("#rePassword").blur(function () {

            if(!rePass()){

                $("#rePassError").html("<div class='errorCode'>这里不可以不填</div>");

            } else if(!rePassCompared()){

                $("#rePassError").html("<div class='errorCode'>两次密码不一样</div>");

            } else {

                $("#rePassError").html("<div class='passCode'>点击保存就完成了</div>");

            }

        })

        function updatePassword() {

//            alert("进入点击事件")

            if (!(oldPass()||newPass()||rePass()||rePassCompared())){

                return false;

            }

            if($("#oldPass").val() != ${user.adminInfo.password}){

                $("#oldPassError").html("<div class='errorCode'>密码不正确</div>");

                return false;
            }

            if($("#oldPass").val() == $("#newPassword").val()){

                $("#newPassError").html("<div class='errorCode'>旧密码不能和新密码一致</div>");

                $("#rePassError").empty();

                $("#newPassword").val("");

                $("#rePassword").val("");

                return false;

            }

            $.post("/userInfo/rewritePassword",{

                id:${user.adminInfo.id},
                password:$("#newPassword").val()

            })

            showResult();
        }

        //保存成功的提示信息
        function showResult() {

            showResultDiv(true);

            window.setTimeout("showResultDiv(false);", 3000);

            setTimeout(result, 3000);


        }
        function showResultDiv(flag) {
            var divResult = document.getElementById("save_result_info");
            if (flag)
                divResult.style.display = "block";
            else
                divResult.style.display = "none";
        }

        function result() {

            window.location.reload();

        }
    </script>

</html>
