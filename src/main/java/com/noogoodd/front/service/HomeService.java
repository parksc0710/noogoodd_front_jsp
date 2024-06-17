package com.noogoodd.front.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final RestTemplate restTemplate;

    @Value("${api.domain}")
    private String apiDomain;
    private final String apiUrlPreFix = "/home";

    public String getHomeMainData() {

        String apiUrlSuffix = "/main";
        String apiUrl = apiDomain + apiUrlPreFix + apiUrlSuffix;
        String requestBody = "";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }
}
