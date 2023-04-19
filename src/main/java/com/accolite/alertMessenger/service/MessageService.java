package com.accolite.alertMessenger.service;

import com.accolite.alertMessenger.exception.MessageNotFoundException;
import com.accolite.alertMessenger.model.Message;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageService {

    Message saveData(Message message);

    List<Message> getData();

    void deleteData(int id);

    Message updateData(Message message, int id);

    public Message publishData(Message message, int messageId) throws MessageNotFoundException;

    public Message getDataById(int messageId) throws MessageNotFoundException;

    public Message acknowledgeData(Message message, int messageId) throws MessageNotFoundException;

    public List<Message> getUnreadDataForUser();

    public List<Message> getReadDataForUser();

    public List<Message> getPublishedData();
}
