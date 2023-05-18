package com.accolite.alertMessenger.service.implementation;

import com.accolite.alertMessenger.model.UserDetail;
import com.accolite.alertMessenger.repository.UserRepo;
import com.accolite.alertMessenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder();
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetail addUser(UserDetail user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return  userRepo.save(user);
    }
    @Override
    public UserDetail login(UserDetail user) throws Exception {
       UserDetail data = userRepo.findByUserId(user.getUserId());
       if(data!=null){
           if(bCryptPasswordEncoder.matches(user.getPassword(),data.getPassword())){
//               data.setUserId(null);
//               data.setPassword(null);
               return data;
           }else{
               System.out.println("Wrong Password");
                 throw new Exception("Wrong Password");
           }
       }
       else{
           System.out.println("null");
           throw new Exception("Usr not Found");
       }
    }
    @Override
    public List<UserDetail> getUser() {
        return userRepo.findAll();
    }

}
