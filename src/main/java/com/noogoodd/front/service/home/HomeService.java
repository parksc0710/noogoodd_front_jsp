package com.noogoodd.front.service.home;

import com.noogoodd.front.utils.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    @Value("${api.domain}")
    private String apiDomain;
    private final String apiUrlPreFix = "/home";

    public String getHomeMainData(String JWT) {

        String apiUrlSuffix = "/main";
        String apiUrl = apiDomain + apiUrlPreFix + apiUrlSuffix;
        String requestBody = "";

        ResponseEntity<String> response = HttpUtils.sendPost(JWT, apiUrl, requestBody, String.class);
        if(response != null && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

}
