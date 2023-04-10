package com.accolite.alertMessenger.controller;

import com.accolite.alertMessenger.model.Message;
import com.accolite.alertMessenger.model.User;
import com.accolite.alertMessenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/fetchUser")
    public List<User> getUser(){
        return userService.getUser();
    }

}
