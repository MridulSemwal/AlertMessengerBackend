//package com.accolite.alertMessenger.service;
//
//import com.accolite.alertMessenger.model.User;
//import com.accolite.alertMessenger.repository.UserRepo;
//import com.accolite.alertMessenger.service.implementation.CustomUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepo userRepo;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUserName(username);
//        if(user == null){
//            throw new UsernameNotFoundException("user not found");
//        }
//        return new CustomUserDetails(user);
//    }
//}
