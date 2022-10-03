package com.usa.boxproy.controller;

import com.usa.boxproy.entities.Box;
import com.usa.boxproy.entities.Message;
import com.usa.boxproy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public List<Message> getAll(){
        return  messageService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Message> getMessage(@PathVariable("id") int idMessage){
        return messageService.getMessage(idMessage);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save(@RequestBody Message mes){
        return messageService.save(mes);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message update(@RequestBody Message message){
        return messageService.update(message);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int idMessage){
        messageService.delete(idMessage);
    }

}
