package com.example.microservice.services.userprofileservice;



import com.example.microservice.entity.User;
import com.example.microservice.repositories.UserRepository;
import com.example.microservice.security.MicroserviceUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class OAuth2ClientTokenSevices implements ClientTokenServices {

    @Autowired
    private UserRepository users;

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
        User user = getClientUser(authentication);
        String accessToken = user.getAccessToken();
        Calendar expirationDate = user.getAccessTokenValidity();

        if (accessToken == null) return null;

        DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        oAuth2AccessToken.setExpiration(expirationDate.getTime());
        return oAuth2AccessToken;
    }

    @Override
    public void saveAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication, OAuth2AccessToken accessToken) {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.setTime(accessToken.getExpiration());
        User user = getClientUser(authentication);
        user.setAccessToken(accessToken.getValue());
        user.setAccessTokenValidity(expirationDate);
        users.save(user);
    }

    @Override
    public void removeAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
        User user = getClientUser(authentication);
        user.setAccessToken(null);
        user.setRefreshToken(null);
        user.setAccessTokenValidity(null);
        users.save(user);
    }


    private User getClientUser(Authentication authentication) {
        MicroserviceUserDetails userDetails = (MicroserviceUserDetails) authentication.getPrincipal();
        Optional<User> clientUser = users.findByUsername(userDetails.getUser().getUsername());
        return clientUser.orElseThrow(() -> new UsernameNotFoundException("user not found" ));
    }

}
