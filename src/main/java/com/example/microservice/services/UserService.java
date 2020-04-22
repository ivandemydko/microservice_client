package com.example.microservice.services;

import com.example.microservice.entity.AuthGroup;
import com.example.microservice.entity.User;
import com.example.microservice.repositories.AuthGroupRepo;
import com.example.microservice.repositories.UserRepository;
import com.example.microservice.security.MicroserviceUserDetails;
import com.example.microservice.security.MicroserviceUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthGroupRepo authGroupRepo;
    @Autowired
    private MicroserviceUserDetailsService microserviceUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User registerNewUserAccount(String name, String password) {
        User newUser = new User(name, password);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser = userRepo.save(newUser);
        AuthGroup authGroup = new AuthGroup(newUser.getId(), "ROLE_USER");
        authGroupRepo.save(authGroup);
        autoLogin(name, password);
        return newUser;
    }


    private void autoLogin(String username, String password) {
        MicroserviceUserDetails userDetails = (MicroserviceUserDetails) microserviceUserDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println("Auto login successfully!: " + username);
        }
    }

    public User getUserFromSecurityContext() {
        MicroserviceUserDetails userDetails = (MicroserviceUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

}
