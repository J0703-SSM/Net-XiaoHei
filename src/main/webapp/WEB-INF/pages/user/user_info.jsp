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
        <script language="javascript" type="text/javascript">
            //保存成功的提示信息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
        </script>
        <style>

            .passCode{

                font-size:10pt;
                color: forestgreen;

            }
            .error_msg{

                font-size:10pt;
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
                <li><a href="user_info.jsp" class="information_on"></a></li>
                <li><a href="user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，数据并发错误！-->
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>管理员账号：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${user.adminInfo.admin_code}" /></div>
                <div class="text_info clearfix"><span>角色：</span></div>
                <div class="input_info">
                    <input type="text" readonly="readonly" class="readonly width400" value="<c:forEach items="${user.adminForRoles}" var="role" varStatus="s"><c:if test="${!s.first}"> , </c:if>${role.roleName}</c:forEach>"/>
                </div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <input type="text" id="cname" value="${user.adminInfo.name}" />
                    <span class="required">*</span>
                    <span id="nameMsg"></span>
                </div>
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" id="phone" value="${user.adminInfo.telephone}" />
                    <span id="phoneMsg"></span>
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <input type="text" id="email" class="width200" value="${user.adminInfo.email}" />
                    <span id="emailMsg"></span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${user.adminInfo.enrollDate}"/></div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="uploadInfo()" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>            
            <p>Powered By XiaoHei</p>
        </div>
    </body>

    <script>

        function namePass() {

            var username = /^[\u4E00-\u9FA5A-Za-z]{2,20}$/;

            if(username.test($("#cname").val())){

                return true;

            }

            return false;

        }

        function numberPass() {

            var pattern =/^(\+\d{2,3}\-)?\d{11}$/;

            if(pattern.test($("#phone").val())){

                return true;

            }

            return false;
        }

        function inNumberPass() {

            var pattern = /^(\d{3,4}\-)?[1-9]\d{6,7}$/;

            if(pattern.test($("#phone").val())){

                return true;

            }

            return false;
        }

        function emailPass() {

            var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;

            if(pattern.test($("#email").val())){

                return true;

            }

            return false;

        }
        $("#cname").blur(function(){

            if(!namePass()){

                $("#nameMsg").html("<span class='error_msg'>20长度以内的汉字、字母的组合</span>");

            }else {

                $("#nameMsg").html("<span class='passCode'>姓名验证通过</span>");

            }

        })

        $("#phone").blur(function(){

            if(numberPass()){

                $("#phoneMsg").html("<span class='passCode'>手机验证成功</span>");

            }else if(inNumberPass()){

                $("#phoneMsg").html("<span class='passCode'>固话验证成功</span>");

            } else {

                $("#phoneMsg").html("<span class='error_msg'>号码验证失败</span>");

            }
        })

        $("#email").blur(function(){

            if(emailPass()){

                var str = $("#email").val().split("@");

                var emailCompany = str[1].split(".");

                $("#emailMsg").html("<span class='passCode'>"+ emailCompany[0] +"邮箱验证成功</span>")

            } else {

                $("#emailMsg").html("<span class='error_msg'>邮箱验证失败</span>")

            }

        });

        function uploadInfo() {

            if(namePass() && emailPass() && (numberPass() || inNumberPass())){

//                alert("判断成功")

                $.post("/userInfo/update",{

                    id:${user.adminInfo.id},
                    name:$("#cname").val(),
                    telephone:$("#phone").val(),
                    email:$("#email").val()

                });

                showResult();
                return false;
            }

            alert("信息填写不完整,请完整后重新提交");
        }

    </script>
</html>
