package com.ecom.productservice.service;

import com.ecom.productservice.entity.User;
import com.ecom.productservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user)
    {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> byUsername =userRepository.findByUsername(username);
        if(byUsername.isEmpty()) throw new UsernameNotFoundException();
        return null;
    }
}
