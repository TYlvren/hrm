<%--
  Created by IntelliJ IDEA.
  User: Lance
  Date: 2019/4/1
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>出错了</title>
</head>
<body>

<div align="center">
    ${exception}<br>
    <a href="${ctx}/WEB-INF/jsp/main.jsp">回到首页</a>
</div>
</body>
</html>
