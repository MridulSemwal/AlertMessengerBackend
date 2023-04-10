package com.accolite.alertMessenger.service.implementation;

import com.accolite.alertMessenger.model.User;
import com.accolite.alertMessenger.repository.UserRepo;
import com.accolite.alertMessenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getUser() {
        return userRepo.findAll();
    }


}
