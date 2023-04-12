package com.accolite.alertMessenger.controller;

import com.accolite.alertMessenger.model.Message;
import com.accolite.alertMessenger.model.User;
import com.accolite.alertMessenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accolite/alertmessenger")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        try{
            return new ResponseEntity<User>(userService.login(user), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
 //   public User login(@RequestBody User user){ return  userService.login(user); }
    @GetMapping("/fetchUser")
    public List<User> getUser(){
        return userService.getUser();
    }

}
