package com.noogoodd.front.controller.user;

import com.noogoodd.front.model.user.UserModel;
import com.noogoodd.front.service.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(Model model){
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(UserModel user){
        HttpStatusCode rtnCode = userService.register(user);
        if(rtnCode == HttpStatus.OK) {
            return "redirect:/";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/signin")
    public String signin(){
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


}
