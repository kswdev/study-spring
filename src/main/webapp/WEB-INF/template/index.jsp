<%--
  Created by IntelliJ IDEA.
  User: koviksw
  Date: 2024-03-05
  Time: 오후 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudfare.com/ajax/libs/semantic-ui/2.2.10/semantic.min.css">
</head>
<body>
    <form method="POST" action="">
        <input type="text" name="username">
        <input type="password" name="password">
        <input type="hidden" name="${csrf_token.parameterName}" value="${csrf_token.token}" />
        <button></button>
    </form>
</body>
</html>
