package com.accolite.alertMessenger.controller;

import com.accolite.alertMessenger.model.Message;
import com.accolite.alertMessenger.model.User;
import com.accolite.alertMessenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accolite/alertmessenger")
public class MessageController {

    @Autowired
    private MessageService messageService;

    //Used to save data
    @PostMapping("/saveData")
    public ResponseEntity<Message> save(@RequestBody Message message){
        Message msg = null;
        try{
        return new ResponseEntity<Message>(messageService.save(message), HttpStatus.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Used to fetch all data for admin
    @GetMapping("/fetchData")
    public List<Message> getData(){
        return messageService.getData();
    }

    //Used to get data by id
    @GetMapping("/getbyid/{id}")
    public Message getDataById(@PathVariable("id") int messageId){
        return messageService.getDataById(messageId);
    }


    //Used to fetch data for users
    @GetMapping(value="/fetchforuser")
    public List<Message> getMessagesForUser(){
        return messageService.getMessagesForUser();
    }

    //used to delete data
    @DeleteMapping(value="/deleteData/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        try{
          messageService.delete(id);
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Used to update data
    @PutMapping(value = "/updateData/{id}")
    public ResponseEntity<Message> updateMessage(@RequestBody Message newMessage, @PathVariable("id") int id){
      try{
          return new ResponseEntity<Message>(messageService.update(newMessage, id),HttpStatus.ACCEPTED);
      }
      catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }

    //Used to update the data so that it can be shown to the users
    @PutMapping(value="/publishing/{id}")
    public Message updateDoneByAdmin(@PathVariable("id") int messageId, @RequestBody Message message){
        return messageService.updateDoneByAdmin(message, messageId);
    }

}
