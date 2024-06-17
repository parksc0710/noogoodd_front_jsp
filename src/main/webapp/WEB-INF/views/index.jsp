<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1>Welcome to Spring Boot with JSP</h1>

<c:choose>
    <c:when test="${not empty homeModel}">
        <p>공지사항 :: ${homeModel.notice}</p>
    </c:when>
</c:choose>


</body>
</html>