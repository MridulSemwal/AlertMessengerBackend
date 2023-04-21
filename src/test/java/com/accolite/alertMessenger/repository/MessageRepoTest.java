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
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest(properties = {
        "spring.test.database.replace=NONE",
        "spring.datasource.url=jdbc:mysql://localhost:3306/alertmessenger"
})
class MessageRepoTest {
    @Autowired
    private MessageRepo messageRepo;
    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldgetUnreadMessagesForUser() {
        List<Message> list=new ArrayList<>();
        Message message1=Message.builder().messageId(1234).acknowledge("YES")
                .acknowledgedBy("CHARU").aircraftRegistration("AIRCRAFT-4").desk("CREW-1").isPublished(1)
                .build();
        Message message2=Message.builder().messageId(11).acknowledge("NO")
                .acknowledgedBy("CHARU").aircraftRegistration("AIRCRAFT-4").desk("CREW-1").isPublished(1)
                .build();
        Message message3=Message.builder().messageId(12).acknowledge("NO")
                .acknowledgedBy("CHARU").aircraftRegistration("AIRCRAFT-4").desk("CREW-1").isPublished(1)
                .build();
        list.add(message1);
        list.add(message2);
        list.add(message3);
        messageRepo.saveAll(list);
        List<Message> list2=messageRepo.getUnreadMessagesForUser();
        assertEquals(2,list2.size());

    }

    @Test
    void shouldgetReadMessagesForUser() {
        List<Message> list=new ArrayList<>();
        Message message1=Message.builder().messageId(1).acknowledge("YES")
                .acknowledgedBy("CHARU").aircraftRegistration("AIRCRAFT-4").desk("CREW-1").isPublished(1)
                .build();
        Message message2=Message.builder().messageId(2).acknowledge("YES")
                .acknowledgedBy("CHARU").aircraftRegistration("AIRCRAFT-4").desk("CREW-1").isPublished(1)
                .build();
        Message message3=Message.builder().messageId(3).acknowledge("NO")
                .acknowledgedBy("CHARU").aircraftRegistration("AIRCRAFT-4").desk("CREW-1").isPublished(1)
                .build();
        list.add(message1);
        list.add(message2);
        list.add(message3);
        messageRepo.saveAll(list);
        List<Message> list2=messageRepo.getReadMessagesForUser();
        assertEquals(2,list2.size());
    }

    @Test
    void getPublishedData() {
        List<Message> list=new ArrayList<>();
        Message message1=Message.builder().messageId(1).acknowledge("YES")
                .acknowledgedBy("CHARU").aircraftRegistration("AIRCRAFT-4").desk("CREW-1").isPublished(1)
                .build();
        Message message2=Message.builder().messageId(2).acknowledge("YES")
                .acknowledgedBy("CHARU").aircraftRegistration("AIRCRAFT-4").desk("CREW-1").isPublished(1)
                .build();
        Message message3=Message.builder().messageId(3).acknowledge("NO")
                .acknowledgedBy("CHARU").aircraftRegistration("AIRCRAFT-4").desk("CREW-1").isPublished(1)
                .build();
        list.add(message1);
        list.add(message2);
        list.add(message3);
        messageRepo.saveAll(list);
        List<Message> list2=messageRepo.getPublishedData();
        assertEquals(3,list2.size());
    }

    @Test
    public void whenPublishedData_thenReturnPublishedData(){
        List<Message> messageList = messageRepo.getPublishedData();
        assertEquals(messageList.size(), publishedMessageList.size());
    }
}