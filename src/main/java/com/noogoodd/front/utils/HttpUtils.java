package com.noogoodd.front.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtils {

    private static RestTemplate restTemplate;

    public HttpUtils(RestTemplate restTemplate) {
        HttpUtils.restTemplate = restTemplate;
    }

    public static <T> ResponseEntity<T> sendPost(String JWT, String apiUrl, String requestBody, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        if (JWT != null && !JWT.isEmpty()) {
            headers.set("Authorization", "Bearer " + JWT);
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            return restTemplate.exchange(apiUrl, HttpMethod.POST, entity, responseType);
        } catch (Exception ex) {
            return null;
        }
    }

}
