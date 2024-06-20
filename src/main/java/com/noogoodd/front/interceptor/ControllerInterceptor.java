package com.noogoodd.front.interceptor;

import ch.qos.logback.core.util.StringUtil;
import com.noogoodd.front.model.user.UserModel;
import com.noogoodd.front.utils.HttpUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ControllerInterceptor implements HandlerInterceptor {

    @Value("${api.domain}")
    private String apiDomain;
    private final String apiGetUserInfoURL = "/user/get";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        checkJWT(request, response);
        return true;
    }

    private void checkJWT(HttpServletRequest request, HttpServletResponse response) {

        String jwt = hasSpecificCookie(request, "token");

        if (!StringUtil.isNullOrEmpty(jwt)) {

            String apiUrl = apiDomain + apiGetUserInfoURL;
            String requestBody = "";

            ResponseEntity<UserModel> userModelResponseEntity = HttpUtils.sendPost(jwt, apiUrl, requestBody, UserModel.class);

            if (userModelResponseEntity != null) {
                request.setAttribute("jwt",jwt);
                request.setAttribute("userToken", userModelResponseEntity.getBody());
            } else {
                invalidateCookie(response, "token");
            }
        }

    }

    private String hasSpecificCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void invalidateCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}