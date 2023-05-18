package com.accolite.alertMessenger.service;

import com.accolite.alertMessenger.model.UserDetail;

import java.util.List;

public interface UserService {
    public UserDetail addUser(UserDetail user);
    public UserDetail login(UserDetail user) throws Exception;
    public List<UserDetail> getUser();
}
