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
    public ResponseEntity<Message> saveData(@RequestBody Message message){
        try{
        return new ResponseEntity<Message>(messageService.saveData(message), HttpStatus.ACCEPTED);
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
        return messageService.getDataForUser();
    }

    //used to delete data
    @DeleteMapping(value="/deleteData/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable("id") int id){
        try{
          messageService.deleteData(id);
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Used to update data
    @PutMapping(value = "/updateData/{id}")
    public ResponseEntity<Message> updateData(@RequestBody Message newMessage, @PathVariable("id") int id){
      try{
          return new ResponseEntity<Message>(messageService.updateData(newMessage, id),HttpStatus.ACCEPTED);
      }
      catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }

    //Used to update the data so that it can be shown to the users
    @PutMapping(value="/publishing/{id}")
    public Message publishData(@PathVariable("id") int messageId, @RequestBody Message message){
        return messageService.publishData(message, messageId);
    }

}
