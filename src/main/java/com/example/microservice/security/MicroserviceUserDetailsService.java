package com.example.microservice.security;


import com.example.microservice.entity.AuthGroup;
import com.example.microservice.entity.User;
import com.example.microservice.repositories.AuthGroupRepo;
import com.example.microservice.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicroserviceUserDetailsService implements UserDetailsService {


    private final UserRepository userRepo;
    private final AuthGroupRepo authGroupRepo;


    public MicroserviceUserDetailsService(UserRepository userRepo, AuthGroupRepo authGroupRepo) {
        this.userRepo = userRepo;
        this.authGroupRepo = authGroupRepo;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("cannot find username: " + username));
        List<AuthGroup> authGroups = authGroupRepo.findByUserId(user.getId());
        return new MicroserviceUserDetails(user, authGroups);
    }
}
