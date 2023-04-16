package com.accolite.alertMessenger.repository;

import com.accolite.alertMessenger.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageRepoTest {

    @Autowired
    private MessageRepo messageRepo;

    //Test to check whether the users are able to see the published messages or not
    @Test
    void checkIfPublishedMessagesAreShownToUser(){
        Message message = new Message(10, "UK-07", "INDIGO",
                "DESK", "CABIN_CREW", "YES", "NO",
                "MRIDUL", "YASH", "HIGH", 1);

        messageRepo.save(message);

        List<Message> messageList= messageRepo.getMessagesForUser();

        Boolean expected = messageList.contains(message);

        assertEquals(expected, true);

    }

}