package com.accolite.alertMessenger.controller;

import com.accolite.alertMessenger.model.UserDetail;
import com.accolite.alertMessenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accolite/alertmessenger")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public UserDetail addUser(@RequestBody @Valid UserDetail user){
        return userService.addUser(user);
    }

    @PutMapping("/login")
    public ResponseEntity<UserDetail> login(@RequestBody UserDetail user) throws Exception {
        return new ResponseEntity<UserDetail>(userService.login(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/fetchUser")
    public List<UserDetail> getUser(){
        return userService.getUser();
    }

}
