package com.accolite.alertMessenger.service;

import com.accolite.alertMessenger.model.Message;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Message saveData(Message message);

    List<Message> getData();

    //ye
    void deleteData(int id);

    //ye
    Message updateData(Message message, int id);

    //Ye
    public Message publishData(Message message, int messageId);

    //Ye
    public Message getDataById(int messageId);

    //Y
    public Message acknowledgeData(Message message, int messageId);

    public List<Message> getUnreadDataForUser();

    public List<Message> getReadDataForUser();

    public List<Message> getPublishedData();
}
