package com.accolite.alertMessenger.service;

import com.accolite.alertMessenger.model.UserDetail;
import com.accolite.alertMessenger.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    private UserDetail user;
    private List<UserDetail> userList = new ArrayList<>();

    BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder();

    @BeforeEach
    void setup(){

        user = UserDetail.builder().userId("mridul").role("ADMIN")
                .password(bCryptPasswordEncoder.encode("1234")).build();

        userList.add(user);

        Mockito.when(userRepo.save(user))
                .thenReturn(user);
        Mockito.when(userRepo.findAll())
                .thenReturn(userList);
        Mockito.when(userRepo.findByUserId(user.getUserId()))
                .thenReturn(user);

    }

    @Test
    public void whenSavingUser_thenUserShouldBeSavedProperly(){

        UserDetail user1 = UserDetail.builder().userId("mridul").role("ADMIN")
                .password("1234").build();

        assertEquals(userService.addUser(user).getUserId(), user.getUserId());

    }

    @Test
    public void whenFetchingUSer_thenAllTheUsersShouldBeFetched(){

        List<UserDetail> userList1 = userService.getUser();
        assertEquals(userList.size(), userList1.size());

    }

    @Test
    public void whenUserTryToLogin_thenReturnCorrectRoleOfTheUser() throws Exception {

        UserDetail user1 = UserDetail.builder().userId("mridul").role("ADMIN")
                .password("1234").build();

        UserDetail resultUser = userService.login(user1);

        assertEquals(resultUser.getRole(), "ADMIN");

    }

    @Test
    public void whenUserTryToLoginWithWrongCredentials_thenThrowException() throws Exception {

        assertThrows(Exception.class, ()->{
            UserDetail user1 = UserDetail.builder().userId("mridul").role("ADMIN")
                    .password("124").build();
            UserDetail resultUser = userService.login(user1);
        });
    }

    @Test
    public void whenSavingUserIncorrectly_thenThrowException(){

        assertThrows(Exception.class, ()->{
            UserDetail user = UserDetail.builder().userId("mridul").role("ADMIN")
                    .build();
            userService.addUser(user);
        });

    }



}