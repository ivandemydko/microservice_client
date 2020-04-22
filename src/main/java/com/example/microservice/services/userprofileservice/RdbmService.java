package com.example.microservice.services.userprofileservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class RdbmService {

    //    @Value("${rdbm.service.url}")
    private String rdbmServiceUrl = "http://localhost:8081";

    @Autowired()
    private OAuth2RestTemplate clientRdbmService;

    public UserProfile getUserProfile() {
        String url = rdbmServiceUrl + "/api/user";
        return clientRdbmService.getForObject(url, UserProfile.class);
    }


}
