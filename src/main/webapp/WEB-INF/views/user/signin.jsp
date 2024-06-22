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

<h1>로그인 페이지</h1>

<form id="loginForm">
    <label for="email">이메일:</label>
    <input type="text" id="email" name="email" required>
    <br>
    <label for="password">비밀번호:</label>
    <input type="password" id="password" name="password" required>
    <br><br>
    <button type="submit">Login</button>
</form>
<br /><br /><br />
<a href="https://accounts.google.com/o/oauth2/auth?client_id=${GOOGLE_CLIENT_ID}&redirect_uri=${GOOGLE_REDIRECT_URL}&response_type=code&scope=profile email">구글 로그인</a>
<br />
<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${KAKAO_CLIENT_ID}&redirect_uri=${KAKAO_REDIRECT_URL}">카카오 로그인</a>


<script>
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        fetch('/user/signin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        })
        .then(response => response.json())
        .then(data => {
            if (data.token) {
                // JWT를 쿠키에 저장
                setCookie('token', data.token);
                alert('Login에 성공했습니다.');
                // 원하는 페이지로 리다이렉트
                window.location.href = '/';
            } else {
                alert('Login에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
</script>



</body>
</html>