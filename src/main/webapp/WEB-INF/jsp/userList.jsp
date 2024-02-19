<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>主页</title>
</head>
<body>
    <h1>欢迎登录，${username}！</h1>
     <p><a href="${pageContext.request.contextPath}/home/logout">注销</a></p>
    <a href="${pageContext.request.contextPath}/users/addPage">添加</a>
    <table>
        <tr>
            <td>id</td>
            <td>用户名</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        <c:forEach var="item" items="${users}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td><a href="${pageContext.request.contextPath}/users/findPage?id=${item.id}">修改</a>
            <td><a href="${pageContext.request.contextPath}/users/del?id=${item.id}">删 除</a></td>
                </tr>
            </c:forEach>
    </table>
</body>
</html>