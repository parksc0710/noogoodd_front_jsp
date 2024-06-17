package com.noogoodd.front.controller;

import com.noogoodd.front.model.home.HomeModel;
import com.noogoodd.front.service.HomeService;
import com.noogoodd.front.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/")
    public String home(Model model) throws IOException {
        String homeModelJson = homeService.getHomeMainData();
        HomeModel homeModel = JsonUtils.parseJsonToModel(homeModelJson, HomeModel.class);
        model.addAttribute("homeModel", homeModel);
        return "index";
    }

}

