package com.noogoodd.front.service.user;

import com.noogoodd.front.model.user.UserModel;
import com.noogoodd.front.utils.HttpUtils;
import com.noogoodd.front.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${api.domain}")
    private String apiDomain;
    private final String apiUrlPreFix = "/user";

    public HttpStatusCode register(UserModel user) {

        user.setUuid(UUID.randomUUID().toString());
        user.setRole("USER");
        user.setSign_type("NORMAL");
        user.setAct_flg(true);

        String apiUrlSuffix = "/register";
        String apiUrl = apiDomain + apiUrlPreFix + apiUrlSuffix;
        String requestBody = JsonUtils.toJson(user);

        ResponseEntity<String> response = HttpUtils.sendPost("", apiUrl, requestBody, String.class);
        if(response != null && response.getStatusCode() == HttpStatus.OK) {
            return HttpStatusCode.valueOf(response.getStatusCode().value());
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatusCode update(UserModel user, String jwt) {
        user.setAct_flg(true);
        String apiUrlSuffix = "/update";
        String apiUrl = apiDomain + apiUrlPreFix + apiUrlSuffix;
        String requestBody = JsonUtils.toJson(user);

        ResponseEntity<String> response = HttpUtils.sendPost(jwt, apiUrl, requestBody, String.class);
        if(response != null && response.getStatusCode() == HttpStatus.OK) {
            return HttpStatusCode.valueOf(response .getStatusCode().value());
        } else {
            return HttpStatus.BAD_REQUEST;
        }

    }

    public ResponseEntity<String> login(String email, String password, String type) {

        Map<String, String> loginUserMap = new HashMap<>();
        loginUserMap.put("email", email);
        loginUserMap.put("password", password);
        loginUserMap.put("type", type);

        String apiUrlSuffix = "/login";
        String apiUrl = apiDomain + apiUrlPreFix + apiUrlSuffix;
        String requestBody = JsonUtils.toJsonFromMap(loginUserMap);

          return HttpUtils.sendPost("", apiUrl, requestBody, String.class);
    }

}
