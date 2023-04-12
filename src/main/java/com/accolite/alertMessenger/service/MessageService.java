package com.accolite.alertMessenger.service;

import com.accolite.alertMessenger.model.Message;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Message save(Message message);

    List<Message> getData();

    void delete(int id);

    Message update(Message message, int id);

    public List<Message> getMessagesForUser();

    public Message updateDoneByAdmin(Message message, int messageId);

    public Message getDataById(int messageId);
}
