package com.example.demo.controller;

import com.example.demo.model.UserLogin;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.credentials.secret}")
    private String appSecret;

    @Value("${keycloak.resource}")
    private String clientId;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", userLogin.getUsername());
        map.add("password",userLogin.getPassword());
        map.add("client_id",clientId);
        map.add("grant_type","password");
        map.add("scope", "openid");
        map.add("client_secret",appSecret);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8180/auth/realms/"+realm+"/protocol/openid-connect/token",
                        HttpMethod.POST,
                        entity,
                        String.class);
        return response;
    }
}
