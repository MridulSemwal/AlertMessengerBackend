package com.accolite.alertMessenger.repository;

import com.accolite.alertMessenger.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDetail, Long> {
    UserDetail findByUserId(String userName);
}
