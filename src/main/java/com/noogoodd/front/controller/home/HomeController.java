package com.noogoodd.front.controller.home;

import com.noogoodd.front.model.home.HomeModel;
import com.noogoodd.front.model.user.UserModel;
import com.noogoodd.front.service.home.HomeService;
import com.noogoodd.front.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/")
    public String home(HttpServletRequest request, @CookieValue(value = "token", defaultValue = "") String JWT, Model model) throws IOException {
        UserModel userToken = (UserModel) request.getAttribute("userToken");
        if (userToken != null) {
            model.addAttribute("userToken", userToken);
        }

        String homeModelJson = homeService.getHomeMainData(JWT);
        if(homeModelJson != null) {
            HomeModel homeModel = JsonUtils.parseJsonToModel(homeModelJson, HomeModel.class);
            model.addAttribute("homeModel", homeModel);
        }

        return "index";
    }

}

