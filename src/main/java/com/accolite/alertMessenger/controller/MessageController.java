package com.accolite.alertMessenger.controller;

import com.accolite.alertMessenger.model.Message;
import com.accolite.alertMessenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accolite/alertmessenger")
@CrossOrigin(origins = "http://localhost:4200/")
public class MessageController {

    @Autowired
    private MessageService messageService;

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

    @GetMapping("/fetchData")
    public ResponseEntity<List<Message>> getData(){

        List<Message> messageList = messageService.getData();
        if(messageList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(messageList));
    }

    @DeleteMapping(value="/deleteData/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        try{
          messageService.delete(id);
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/updateData/{id}")
    public ResponseEntity<Message> updateMessage(@RequestBody Message newMessage, @PathVariable("id") int id){
      try{
          return new ResponseEntity<Message>(messageService.update(newMessage, id),HttpStatus.ACCEPTED);
      }
      catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }

}
