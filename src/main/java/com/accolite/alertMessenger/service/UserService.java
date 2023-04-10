package com.accolite.alertMessenger.service;

import com.accolite.alertMessenger.model.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);

    public List<User> getUser();
}
