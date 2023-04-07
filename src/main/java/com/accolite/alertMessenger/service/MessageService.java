package com.accolite.alertMessenger.service;

import com.accolite.alertMessenger.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Message save(Message message);

    List<Message> getData();

    void delete(int id);

    Message update(Message message, int id);

}
