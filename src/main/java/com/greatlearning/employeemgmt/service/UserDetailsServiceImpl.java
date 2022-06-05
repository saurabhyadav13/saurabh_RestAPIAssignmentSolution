package com.greatlearning.employeemgmt.service;

import com.greatlearning.employeemgmt.repository.UserRepository;
import com.greatlearning.employeemgmt.entity.User;
import com.greatlearning.employeemgmt.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Could not find User");
        }

        return new MyUserDetails(user);
    }
    public User createUser(User user) {
        String rawPassword;
        rawPassword = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        user.setPassword(rawPassword);
        return user;
    }
}
