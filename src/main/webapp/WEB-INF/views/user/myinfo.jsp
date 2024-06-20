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
    <label for="password">비밀번호</label>
    <input type="password" id="password" name="password" required>
    <br/><br/>
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

</body>
</html>