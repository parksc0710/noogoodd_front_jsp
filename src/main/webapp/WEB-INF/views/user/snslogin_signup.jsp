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
<h1>${provider} 회원가입 페이지</h1>

<form action="${pageContext.request.contextPath}/user/signup" method="post">

    <label for="email">이메일</label>
    <input type="email" id="email" name="email" placeholder="you@example.com" value="${email}" readonly>
    <input type="hidden" id="password" name="password" value="" />
    <br/><br/>
    <label for="nickname">닉네임</label>
    <input type="text" id="nickname" name="nickname" required>
    <br/><br/>
    <label >장애여부</label>
    <input type="radio" class="disability_yn" name="disability_yn" value="true"> 장애인
    <input type="radio" class="disability_yn" name="disability_yn" value="false"> 비장애인
    <br/><br/>
    <label for="disability_type">장애유형</label>
    <input type="text" id="disability_type" name="disability_type">
    <br/><br/>
    <label for="aid_type">보조기기</label>
    <input type="text" id="aid_type" name="aid_type">
    <br/><br/>
    <label for="address_area">지역</label>
    <input type="text" id="address_area" name="address_area">
    <br/><br/>
    <label >성별</label>
    <input type="radio" class="gender" name="gender" value="male"> 남자
    <input type="radio" class="gender" name="gender" value="female"> 여자
    <br/><br/>
    <label for="birth_day">생년월일</label>
    <input type="text" id="birth_day" name="birth_day">
    <input type="hidden" id="sign_type" name="sign_type" value="${provider}" />
    <br/><br/><br/><br/>
    <button>가입 완료</button>

</form>

</body>
</html>