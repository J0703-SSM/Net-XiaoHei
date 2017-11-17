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
        <link rel="stylesheet" type="text/css" href="../../../w2ui-1.5.rc1/w2ui-1.5.rc1.css" />
        <script src="../../../BS/js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="../../../w2ui-1.5.rc1/w2ui-1.5.rc1.js"></script>
        <script language="javascript" type="text/javascript">
            function deleteRole() {
                var r = window.confirm("确定要删除此角色吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
        </script>
        <style>

            .line{

                position:absolute;
                left:400px; /*左右位置*/
                top:84px;/*上下位置*/

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
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='/role/addRole';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../../../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr>
                        <c:forEach items="${roles}" var="roleBox">
                        <tr id="${roleBox.roleId}">
                            <td>${roleBox.roleId}</td>
                            <td id="${roleBox.roleName}">${roleBox.roleName}</td>
                            <td id="${roleBox.roleId}value">

                                <c:forEach items="${roleBox.roles}" var="role" varStatus="s">

                                    <c:if test="${!s.first}">,</c:if>

                                    ${role.value}

                                </c:forEach>

                            </td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='role_modi.jsp';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deletePop('${roleBox.roleName}','${roleBox.roleId}value','${roleBox.roleId}');" />
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                </div> 
                <!--分页-->
                <%--<div id="pages">--%>
        	        <%--<a href="#">上一页</a>--%>
                    <%--<a href="#" class="current_page">1</a>--%>
                    <%--<a href="#">2</a>--%>
                    <%--<a href="#">3</a>--%>
                    <%--<a href="#">4</a>--%>
                    <%--<a href="#">5</a>--%>
                    <%--<a href="#">下一页</a>--%>
                <%--</div>--%>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[Thanks for W2UI]</p>
            <p>Powered By XiaoHei </p>
        </div>
    </body>
<script type="text/javascript">
    function deletePop(roleName,roleIdValue,roleId) {
        w2popup.open({
            width   : 580,
            height  : 350,
            title   : '确认删除',
            body    : '<div class="w2ui-centered" style="line-height: 1.8">'+
                    '     <h3>要删除的角色:'+ roleName +
                    '</h3><br>关联的权限:'+ $("#" + roleIdValue).text()+ '<br><br>'+
                    '<div style="text-align: center">验证码: <input class="w2ui-input" id="code" style="margin-bottom: 5px">'+
                    '<div class="line"><img src="/universal/codeImg?a='+Math.random()+'" alt="验证码" title="点击更换" id="verifyCodeImage" onclick="changeImage()"/></div>'+
                    '</div>',
            buttons : '<button class="w2ui-btn" onclick="passCode('+ roleId +')">确认删除</button>'+
            '<button class="w2ui-btn" onclick="w2popup.close()">取消</button>'
        });

    }

    <%--点击更新验证--%>
    function changeImage() {

        $("#verifyCodeImage").attr('src', '/universal/codeImg?a=' + Math.random())

    }

    function passCode(roleId) {

        $.post("/universal/passCode",{codeText:$("#code").val()},function (date) {

            if(date['codeTrue'] != null){



                /* 嵌套ajax */
                $.post("/role/deleteRole",{roleId:roleId},function (result) {

                    if(result.resultCode == 0){

                        w2alert(result.message);

                        /* 删除页面元素,完成局部刷新 */
                        $("#" + roleId).remove();

                        window.setTimeout("w2popup.close();",1200);

                    }

                    if(result.resultCode == 1){

                        w2alert(result.message);

                        window.setTimeout("w2popup.close();",4000);

                    }

                });



            }

            if(date['codeFalse'] != null){

                w2alert(date['codeFalse'])

            }

        })

    }
</script>
</html>
