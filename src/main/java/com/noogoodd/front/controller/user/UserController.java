package com.noogoodd.front.controller.user;

import ch.qos.logback.core.util.StringUtil;
import com.noogoodd.front.model.user.UserModel;
import com.noogoodd.front.service.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@PropertySource("classpath:application-oauth2.properties")
public class UserController {

    @Value("${api.domain}")
    private String apiDomain;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String GOOGLE_REDIRECT_URL;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URL;

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(Model model){
        return "user/signup";
    }

    @GetMapping("/snslogin_signup/{provider}")
    public String snslogin_signup(@PathVariable String provider, HttpServletRequest request, Model model, HttpServletResponse response){

        String code = request.getParameter("code");
        ResponseEntity<String> rtn = userService.sns_login(provider, code);
        String getUserOauthInfo = rtn.getBody();

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if(!StringUtil.isNullOrEmpty(getUserOauthInfo) && getUserOauthInfo.matches(emailRegex)) {
            // JWT 토클이 아니라 이메일 타입으로 내려오면 소셜회원가입으로
            model.addAttribute("provider", provider.toUpperCase());
            model.addAttribute("email", getUserOauthInfo);
            return "user/snslogin_signup";
        } else if(!StringUtil.isNullOrEmpty(getUserOauthInfo)){
            // JWT 토큰이 내려오면 쿠키 세팅 후 메인 이돟
            Cookie jwtCookie = new Cookie("token", getUserOauthInfo);
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);
            return "redirect:/";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/signup")
    public String signup(UserModel user, HttpServletResponse response){
        HttpStatusCode rtnCode = userService.register(user);
        if(rtnCode == HttpStatus.OK) {

            // 소셜로그인 가잆 시 자동 로그인 처리
            if(!user.getSign_type().equals("NORMAL")) {
                ResponseEntity<String> rtn = userService.login(user.getEmail(), "", user.getSign_type());
                String getUserOauthInfo = rtn.getBody();
                Cookie jwtCookie = new Cookie("token", getUserOauthInfo);
                jwtCookie.setPath("/");
                response.addCookie(jwtCookie);
                return "redirect:/";
            }

            return "redirect:/";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/signin")
    public String signin(Model model){
        model.addAttribute("apiDomain", apiDomain);
        model.addAttribute("GOOGLE_CLIENT_ID", GOOGLE_CLIENT_ID);
        model.addAttribute("GOOGLE_REDIRECT_URL", GOOGLE_REDIRECT_URL);
        model.addAttribute("KAKAO_CLIENT_ID", KAKAO_CLIENT_ID);
        model.addAttribute("KAKAO_REDIRECT_URL", KAKAO_REDIRECT_URL);
        return "user/signin";
    }

    @PostMapping("/signin")
    @ResponseBody
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        String type = "NORMAL";

        ResponseEntity<String> response = userService.login(email, password, type);

        Map<String, String> responseBody = new HashMap<>();
        if (response != null && response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            String token = response.getBody();
            responseBody.put("token", token);
        } else {
            responseBody.put("message", "Login failed");
        }

        return responseBody;
    }

    @GetMapping("/signout")
    public String signout(HttpServletResponse response){
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/myinfo")
    public String myinfo(HttpServletRequest request, Model model){
        UserModel userToken = (UserModel) request.getAttribute("userToken");
        if (userToken != null) {
            model.addAttribute("userToken", userToken);
            return "user/myinfo";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/myinfo")
    public String myinfo(HttpServletRequest request, UserModel user) {
        String jwt = (String) request.getAttribute("jwt");
        HttpStatusCode rtnCode = userService.update(user, jwt);
        if(rtnCode == HttpStatus.OK) {
            return "redirect:/";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/withdraw")
    public String withdraw(HttpServletRequest request, HttpServletResponse response, UserModel user) {
        String jwt = (String) request.getAttribute("jwt");
        HttpStatusCode rtnCode = userService.withdraw(user, jwt);
        if(rtnCode == HttpStatus.OK) {
            Cookie cookie = new Cookie("token", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            return "redirect:/error";
        }
    }

}
