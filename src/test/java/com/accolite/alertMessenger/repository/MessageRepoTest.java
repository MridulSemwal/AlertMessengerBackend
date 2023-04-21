package com.accolite.alertMessenger.repository;

import com.accolite.alertMessenger.model.Message;
import com.accolite.alertMessenger.service.implementation.MessageServiceImplementation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
////@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
////@ContextConfiguration(classes = {MessageRepo.class, MessageServiceImplementation.class})
class MessageRepoTest {

    @Autowired
    private MessageRepo messageRepo;

    Message message, readMessage, unreadMessage;
    List<Message> publishedMessageList = new ArrayList<>();
    List<Message> unreadMessageList = new ArrayList<>();
    List<Message> readMessageList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        UUID id1 = UUID.fromString("ac696dd8-51f1-4c9b-b7a2-27dff80ad5b7");
         message = Message
                .builder()
                .flight("AIR-INDIA")
                .messageId(id1)
                .desk("DESK")
                .acknowledge("NO")
                .acknowledgedBy("MRIDUL")
                .aircraftRegistration("UK-07")
                .deskCategory("PILOT")
                .isPublished(1)
                .escalated("NO")
                .priority("HIGH")
                .received("YASH")
                .build();

        UUID id2 = UUID.fromString("d2485c29-b3c1-46b4-8554-303986a9bc3e");
         readMessage = Message
                .builder()
                .messageId(id2)
                .desk("DESK")
                .acknowledge("YES")
                .acknowledgedBy("MRIDUL")
                .aircraftRegistration("UK-07")
                .deskCategory("PILOT")
                .isPublished(1)
                .escalated("NO")
                .priority("HIGH")
                .received("YASH")
                .build();

        UUID id3 = UUID.fromString("61ad0949-9d9c-4af7-b99c-29b45cd3ac29");
         unreadMessage = Message
                .builder()
                .messageId(id3)
                .desk("DESK")
                .acknowledge("NO")
                .acknowledgedBy("MRIDUL")
                .aircraftRegistration("UK-07")
                .deskCategory("PILOT")
                .isPublished(1)
                .escalated("NO")
                .priority("HIGH")
                .received("YASH")
                .build();

        publishedMessageList.add(message);
        publishedMessageList.add(unreadMessage);
        publishedMessageList.add(readMessage);

        unreadMessageList.add(unreadMessage);
        readMessageList.add(readMessage);

        messageRepo.save(message);
        messageRepo.save(unreadMessage);
        messageRepo.save(readMessage);

    }

    @Test
    public void whenPublishedData_thenReturnPublishedData(){
        List<Message> messageList = messageRepo.getPublishedData();
        assertEquals(messageList.size(), publishedMessageList.size());
    }
}