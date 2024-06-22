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
<h1>회원 정보 수정 페이지</h1>

<form action="/user/myinfo" method="post">

    <label for="email">이메일</label>
    <input type="email" id="email" name="email" value="${userToken.email}" readonly>
    <br/><br/>
    <c:choose>
        <c:when test="${'NORMAL' eq userToken.sign_type}">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
            <br/><br/>
        </c:when>
        <c:otherwise>
            <input type="hidden" id="password" name="password" value="">
        </c:otherwise>
    </c:choose>
    <label for="nickname">닉네임</label>
    <input type="text" id="nickname" name="nickname" value="${userToken.nickname}">
    <br/><br/>
    <label >장애여부</label>
    <input type="radio" class="disability_yn" name="disability_yn" value="true" <c:if test="${userToken.disability_yn}">checked</c:if>> 장애인
    <input type="radio" class="disability_yn" name="disability_yn" value="false" <c:if test="${!userToken.disability_yn}">checked</c:if>> 비장애인
    <br/><br/>
    <label for="disability_type">장애유형</label>
    <input type="text" id="disability_type" name="disability_type" value="${userToken.disability_type}">
    <br/><br/>
    <label for="aid_type">보조기기</label>
    <input type="text" id="aid_type" name="aid_type" value="${userToken.aid_type}">
    <br/><br/>
    <label for="address_area">지역</label>
    <input type="text" id="address_area" name="address_area" value="${userToken.address_area}">
    <br/><br/>
    <label >성별</label>
    <input type="radio" class="gender" name="gender" value="male" <c:if test="${userToken.gender == 'male'}">checked</c:if>> 남자
    <input type="radio" class="gender" name="gender" value="female" <c:if test="${userToken.gender != 'male'}">checked</c:if>> 여자
    <br/><br/>
    <label for="birth_day">생년월일</label>
    <input type="text" id="birth_day" name="birth_day" value="${userToken.birth_day}">
    <br/><br/><br/><br/>
    <button>수정하기</button>

</form>
<br/><br/>
    <form action="/user/withdraw" method="post">
        <div style="display: none;">
            <input type="hidden" name="email" value="${userToken.email}" readonly>
            <input type="hidden" name="nickname" value="${userToken.nickname}">
            <input type="hidden" name="sign_type" value="${userToken.sign_type}" readonly>
            <input type="radio" class="disability_yn" name="disability_yn" value="true" <c:if test="${userToken.disability_yn}">checked</c:if>> 장애인
            <input type="radio" class="disability_yn" name="disability_yn" value="false" <c:if test="${!userToken.disability_yn}">checked</c:if>> 비장애인
            <input type="radio" class="gender" name="gender" value="male" <c:if test="${userToken.gender == 'male'}">checked</c:if>> 남자
            <input type="radio" class="gender" name="gender" value="female" <c:if test="${userToken.gender != 'male'}">checked</c:if>> 여자
            <input type="hidden" name="disability_type" value="${userToken.disability_type}">
            <input type="hidden" name="aid_type" value="${userToken.aid_type}">
            <input type="hidden" name="address_area" value="${userToken.address_area}">
            <input type="hidden" name="birth_day" value="${userToken.birth_day}">
        </div>
        <button type="submit">탈퇴하기</button>
    </form>
</body>
</html>