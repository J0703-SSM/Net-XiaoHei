<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="../../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../../styles/global_color.css" />
    </head>
    <body class="index">
        <!--导航区域开始-->
        <div id="index_navi">
            <ul id="menu">

                <li><a href="/universal/intoIndex" class="index_on"></a></li>

                    <c:if test="${mapInfo['1'] != null}">
                        <li><a href="/role/roleList" class="role_off"></a></li>
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
    </body>
</html>
