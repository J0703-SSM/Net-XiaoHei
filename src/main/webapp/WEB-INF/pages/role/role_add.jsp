<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="../../../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../../../styles/global_color.css" />
        <link type="text/css" rel="stylesheet" href="../../../BS/css/bootstrap.css" />
        <script src="../../../BS/js/jquery-3.2.1.js"></script>
        <script src="../../../BS/js/bootstrap.js"></script>
        <script language="javascript" type="text/javascript">
            //保存成功的提示消息
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
                <li><a href="../index.jsp" class="index_off"></a></li>
                <li><a href="role_list.jsp" class="role_on"></a></li>
                <li><a href="../admin/admin_list.jsp" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.jsp" class="fee_off"></a></li>
                <li><a href="../account/account_list.jsp" class="account_off"></a></li>
                <li><a href="../service/service_list.jsp" class="service_off"></a></li>
                <li><a href="../bill/bill_list.jsp" class="bill_off"></a></li>
                <li><a href="../report/report_list.jsp" class="report_off"></a></li>
                <li><a href="../user/user_info.jsp" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，角色名称重复！-->
            <form id="form" method="post" action="/role/addRoleNexus" class="main_form">
                <input type="hidden" name="email" value="${user.adminInfo.email}">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" id="nameText" name="roleName" />
                    <span class="required">*</span>
                    <span id="nameMsg"></span>
                    <%--<div class="validate_msg_medium">不能为空，且为20长度的字母、数字和汉字的组合</div>--%>
                </div>                    
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>
                            <c:forEach items="${roleList}" var="role">
                            <li><input type="checkbox" name="role" value="${role.privilegeId}"/>${role.privilegeName}</li>
                            </c:forEach>
                            <%--<li><input type="checkbox" />角色管理</li>--%>
                            <%--<li><input type="checkbox" />资费管理</li>--%>
                            <%--<li><input type="checkbox" />账务账号</li>--%>
                            <%--<li><input type="checkbox" />业务账号</li>--%>
                            <%--<li><input type="checkbox" />账单</li>--%>
                            <%--<li><input type="checkbox" />报表</li>--%>
                        </ul>
                    </div>
                    <span class="required">*</span>
                    <%--<div class="validate_msg_tiny">至少选择一个权限</div>--%>
                    <%-- 隐藏表单存储临时数据 --%>
                    <input type="hidden" id="data">
                </div>
                <div class="button_info clearfix">
                    <input type="button" id="submitBut" value="保存" class="btn_save" onclick="addRole()"/>
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form>
        </div>

        <div class="modal fade bs-example-modal-lg" id="mymodal-data" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    角色添加成功,前去邮箱激活后才能使用该角色!
                </div>
            </div>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[Thank for bootcss]</span>
            <br />
            <span>Powered By XiaoHei </span>
        </div>
    </body>
    <script>

        /* 验证角色名 */
        $("#nameText").blur(function () {

            $.post("/role/passRoleName",{

                roleName:$("#nameText").val()

            },function (result) {

                if(result.resultCode == 0){

                    $("#nameMsg").html("<div class='passCode'>"+result.message+"</div>")

                    $("#data").val(result.resultCode)

                }else {

                    $("#nameMsg").html("<div class='errorCode'>"+result.message+"</div>")

                    $("#data").val(result.resultCode)

                }

            })

        })

        /* 提交表单验证 */
        function addRole() {

            if($("input[name='role']:checked").length == 0){

                alert("请至少选一个权限");

                return false;

            }

            if( $("#data").val() != 0 || $("#data").val() == ""){

                alert("信息填写有误");

                return false;

            }

//            alert("通过");

            /* 弹出窗体 */
            $("#mymodal-data").modal();

            $("#form").submit();

        }
    </script>
</html>
