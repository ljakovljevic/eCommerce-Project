package com.eCommerce.backend.service;

import com.eCommerce.backend.model.User;
import com.eCommerce.backend.model.UserPrincipal;
import com.eCommerce.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if (user == null){
            System.out.println("User Not Found!");
            throw new UsernameNotFoundException("User Not Found!");
        }

        return new UserPrincipal(user);
    }
}
