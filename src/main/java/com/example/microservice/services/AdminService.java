package com.example.microservice.services;


import com.example.microservice.services.guestservice.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private String guestServiceUrl = "http://localhost:8100";

    @Autowired
    private OAuth2RestTemplate clientForGuestService;

    public List<Guest> getAllGuests() {
        String url = guestServiceUrl + "/guests";
        HttpEntity<String> request = new HttpEntity<>(null, null);
        return clientForGuestService.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<Guest>>() {
        }).getBody();
    }

}
