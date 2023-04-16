package com.accolite.alertMessenger.service.implementation;

import com.accolite.alertMessenger.model.Message;
import com.accolite.alertMessenger.model.User;
import com.accolite.alertMessenger.repository.MessageRepo;
import com.accolite.alertMessenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImplementation implements MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Override
    public Message saveData(Message message) {
        return messageRepo.save(message);
    }

    @Override
    public List<Message> getData() {
        return messageRepo.findAll();
    }

    @Override
    public void deleteData(int id) {
        messageRepo.deleteById(id);
    }

    @Override
    public Message updateData(Message newMessage, int id) {
        return messageRepo.findById(id)
                .map(message ->{
                      message.setAircraftRegistration(newMessage.getAircraftRegistration());
                      message.setFlight(newMessage.getFlight());
                      message.setDesk(newMessage.getDesk());
                      message.setDeskCategory(newMessage.getDeskCategory());
                      message.setEscalated(newMessage.getEscalated());
                      message.setAcknowledge(newMessage.getAcknowledge());
                      message.setAcknowledgedBy(newMessage.getAcknowledgedBy());
                      message.setReceived(newMessage.getReceived());
                      message.setPriority(newMessage.getPriority());
                    return messageRepo.save(message);
                })
                .orElseGet(()->{
                    return messageRepo.save(newMessage);
                });
    }

    @Override
    public List<Message> getDataForUser() {
        return messageRepo.getMessagesForUser();
    }

    @Override
    public Message publishData(Message message, int messageId) {
        Message messageToBeUpdated = messageRepo.findById(messageId).get();
        messageToBeUpdated.setIsPublished(1);
        return messageRepo.save(messageToBeUpdated);
    }

    @Override
    public Message getDataById(int messageId) {
        return messageRepo.findById(messageId).get();
    }
}
