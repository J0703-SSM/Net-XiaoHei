<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="../../../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../../../styles/global_color.css" />
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
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="/universal/intoIndex" class="index_off"></a></li>

                <c:if test="${mapInfo['1'] != null}">
                    <li><a href="/role/roleList" class="role_on"></a></li>
                </c:if>

                <c:if test="${mapInfo['2'] != null}">
                    <li><a href="/role/adminList" class="admin_off"></a></li>
                </c:if>

                <c:if test="${mapInfo['3'] != null}">
                    <li><a href="fee/fee_list.jsp" class="fee_off"></a></li>
                </c:if>

                <c:if test="${mapInfo['4'] != null}">
                    <li><a href="account/account_list.jsp" class="account_off"></a></li>
                </c:if>

                <c:if test="${mapInfo['5'] != null}">
                    <li><a href="service/service_list.jsp" class="service_off"></a></li>
                </c:if>

                <c:if test="${mapInfo['6'] != null}">
                    <li><a href="bill/bill_list.jsp" class="bill_off"></a></li>
                </c:if>

                <c:if test="${mapInfo['7'] != null}">
                    <li><a href="report/report_list.jsp" class="report_off"></a></li>
                </c:if>

                <li><a href="/userInfo/into" class="information_off"></a></li>
                <li><a href="/userInfo/intoRewrite" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">           
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" value="超级管理员" />
                    <span class="required">*</span>
                    <div class="validate_msg_medium error_msg">不能为空，且为20长度的字母、数字和汉字的组合</div>
                </div>                    
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>
                            <li><input type="checkbox" checked />管理员管理</li>
                            <li><input type="checkbox" checked />角色管理</li>
                            <li><input type="checkbox" checked />资费管理</li>
                            <li><input type="checkbox" checked />账务账号</li>
                            <li><input type="checkbox" checked />业务账号</li>
                            <li><input type="checkbox" checked />账单</li>
                            <li><input type="checkbox" checked />报表</li>
                        </ul>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny">至少选择一个权限</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form> 
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
             <span>版权所有(C)云科技有限公司 </span>
        </div>
    </body>
</html>
