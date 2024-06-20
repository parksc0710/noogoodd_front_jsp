<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <script src="<c:url value='/js/common/common.js'/>"></script>
</head>
<body>

<h1>누구든든</h1>

<c:choose>
    <c:when test="${not empty homeModel}">
        <p>공지사항 :: ${homeModel.notice}</p>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${not empty userToken}">
        <p>${userToken.nickname}님 안녕하세요!</p>
        <a href="/user/myinfo"1>회원정보수정</a>
        <br/><br/>
        <a href="/user/signout"1>로그아웃</a>
    </c:when>
    <c:otherwise>
        <a href="/user/signin">로그인</a>
        <a href="/user/signup">회원가입</a>
    </c:otherwise>
</c:choose>

</body>
</html>