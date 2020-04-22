package com.example.microservice.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@EnableOAuth2Client
@Component()
public class OAuth2ClientForGuestService {

    private static final String AUTH_TOKEN_URL="/oauth/token";
    private String guestServiceUrl ="http://localhost:8100";

    @Bean("clientForGuestService")
    public OAuth2RestTemplate restTemplateForGuestService(){
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(guestServiceUrl + AUTH_TOKEN_URL);
        resource.setClientId("microservice_app");
        resource.setClientSecret("secret");
        resource.setGrantType("client_credentials");
        resource.setScope(new ArrayList<>(){{add("READ_ALL_GUESTS");add("WRITE_GUEST");add("UPDATE_GUEST");}});
        resource.setAuthenticationScheme(AuthenticationScheme.form);
        AccessTokenRequest request = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext(request));
    }
}
