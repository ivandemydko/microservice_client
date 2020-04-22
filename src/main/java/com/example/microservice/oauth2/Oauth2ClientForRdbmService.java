package com.example.microservice.oauth2;


import com.example.microservice.services.userprofileservice.OAuth2ClientTokenSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@EnableOAuth2Client
@Component
public class Oauth2ClientForRdbmService {

    @Autowired
    private OAuth2ClientTokenSevices clientTokenServices;

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Bean("clientRdbmService")
    public OAuth2RestTemplate oauth2RestTemplate() {
        OAuth2ProtectedResourceDetails resourceDetails = authorizationCode();
        OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails, oauth2ClientContext);
        AccessTokenProviderChain provider = new AccessTokenProviderChain(
                Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
        provider.setClientTokenServices(clientTokenServices);
        template.setAccessTokenProvider(provider);
        return template;
    }


    private OAuth2ProtectedResourceDetails authorizationCode() {
        AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
        resourceDetails.setId("oauth2server");
        resourceDetails.setTokenName("oauth_token");
        resourceDetails.setClientId("microservice_authorization_code");
        resourceDetails.setClientSecret("123456");
        resourceDetails.setAccessTokenUri("http://localhost:8081/oauth/token");
        resourceDetails.setUserAuthorizationUri("http://localhost:8081/oauth/authorize");
        resourceDetails.setScope(Arrays.asList("read_profile"));
        resourceDetails.setPreEstablishedRedirectUri(("http://localhost:8080/user/profile"));
        resourceDetails.setUseCurrentUri(false);
        resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
        return resourceDetails;
    }

}
