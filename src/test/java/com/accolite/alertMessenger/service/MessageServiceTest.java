package com.accolite.alertMessenger.service;

import com.accolite.alertMessenger.model.Message;
import com.accolite.alertMessenger.repository.MessageRepo;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MessageServiceTest {
    @MockBean
    private MessageRepo messageRepo;

    @BeforeEach
     void setUp() {

        Message message = Message
                .builder()
                .messageId(1)
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

        Message readMessage = Message
                .builder()
                .messageId(1)
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

        Message unreadMessage = Message
                .builder()
                .messageId(1)
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

        List<Message> unreadMessageList = new ArrayList<>();
        unreadMessageList.add(unreadMessage);

        List<Message> readMessageList = new ArrayList<>();
        readMessageList.add(readMessage);

        List<Message> messageList = new ArrayList<>();
        messageList.add(message);

        List<Message> publishedMessage = new ArrayList<>();
        publishedMessage.add(readMessage);
        publishedMessage.add(unreadMessage);
        publishedMessage.add(message);

        when(messageRepo.findById(1))
                .thenReturn(Optional.of(message));

        when(messageRepo.findAll())
                .thenReturn(messageList);

        Mockito.when(messageRepo.save(message))
                .thenReturn(message);

        Mockito.when(messageRepo.getUnreadMessagesForUser())
                .thenReturn(unreadMessageList);

        Mockito.when(messageRepo.getReadMessagesForUser())
                .thenReturn(readMessageList);

        Mockito.when(messageRepo.getPublishedData())
                .thenReturn(publishedMessage);

    }

    @Autowired
    private MessageService messageService;

    @Test
    @DisplayName("Get Valid Message Based On the Message ID")
    @Order(3)
    public void whenValidMessageId_ThenReturnValidMessage(){

        int messageId = 1;

       Message message = messageService.getDataById(messageId);

        assertEquals(messageId, message.getMessageId());

    }

    @Test
    @DisplayName("When we save data then it should be properly saved")
    @Order(1)
    public void whenSavingData_ThenSaveProperly(){

        Message message = Message
                .builder()
                .messageId(1)
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

        Message savedData = messageService.saveData(message);

        assertEquals(message, savedData);

    }

    @Test
    @DisplayName("All the data should be fetched correctly")
    @Order(2)
    public void whenFetchingAllData_ThenReturnAllData(){

        List<Message> fetchedMessages  = messageService.getData();
        assertEquals(fetchedMessages.size(), 1);

    }

    @Test
    @Order(10)
    public void whenCorrectMessageIdGiven_ThenDeleteTheMessage(){
        messageService.deleteData(1);
        Mockito.verify(messageRepo).deleteById(1);
    }

    @Test
    @Order(4)
    public void whenUpdateData_ThenTheDataShouldBeUpdated(){

        int messageId = 1;

        Message messageToBeUpdated = Message
                .builder()
                .messageId(1)
                .desk("DESK")
                .acknowledge("NO")
                .acknowledgedBy("MRIDUL SEMWAL")
                .aircraftRegistration("UK-07")
                .deskCategory("PILOT")
                .isPublished(1)
                .escalated("NO")
                .priority("HIGH")
                .received("YASH")
                .build();

        Message updatedMessage = messageService.updateData(messageToBeUpdated, messageId);
        assertEquals(messageToBeUpdated, updatedMessage);
    }


    @Test
    @Order(5)
    public void whenPublishData_ThenMessageShouldBePublished(){

        int messageId = 1;

        Message messageToBePublished = Message
                .builder()
                .messageId(1)
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

        Message publishedMessage = messageService.publishData(messageToBePublished, messageId);

        assertEquals(publishedMessage, messageToBePublished);
    }

    @Test
    @Order(6)
    public void whenDataIsAcknowledged_ThenDataShoulBeAcknowledged(){

        int messageId = 1;

        Message messageToBeAcknowledged = Message
                .builder()
                .messageId(1)
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

        Message acknowledgedMessage = messageService.acknowledgeData(messageToBeAcknowledged, messageId);
        assertEquals(acknowledgedMessage.getAcknowledge(), "YES");

    }

    @Test
    @Order(7)
    public void whenFetchingUnreadMessages_ThenOnlyUnreadMessagesShouldBeReturned(){
        List<Message> unreadMessageList = messageService.getUnreadDataForUser();
        assertEquals(unreadMessageList.size(), 1);
    }

    @Test
    @Order(8)
    public void whenFetchingReadMessages_ThenOnlyReadMessagesShouldBeReturned(){
        List<Message> readMessageList = messageService.getReadDataForUser();
        assertEquals(readMessageList.size(), 1);
    }

    @Test
    @Order(9)
    public void whenFetchingPublishedMessages_ThenOnlyPublishedMessagesShouldBeReturned(){
        List<Message> publishedMessageList = messageService.getPublishedData();
        assertEquals(publishedMessageList.size(), 3);
    }

}