package com.example.demo.controller;

import com.example.demo.model.UserLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.credentials.secret}")
    private String appSecret;

    @Value("${keycloak.resource}")
    private String clientId;

    @GetMapping("/users")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id",clientId);
//        map.add("grant_type","password");
        map.add("scope", "openid");
        map.add("client_secret",appSecret);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8180/auth/realms/"+realm+"/users",
                        HttpMethod.GET,
                        entity,
                        String.class);
        return response;
    }
}
