package com.accolite.alertMessenger.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String userName;
    private String userPassword;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messageList = new ArrayList<>();


}
