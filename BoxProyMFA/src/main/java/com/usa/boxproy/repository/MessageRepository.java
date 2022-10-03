package com.usa.boxproy.repository;

import com.usa.boxproy.entities.Message;
import com.usa.boxproy.repository.crudRepository.MessageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepository {

    @Autowired
    private MessageCrudRepository messageCrudRepository;

    public List<Message> getAll(){
        return (List<Message>) messageCrudRepository.findAll();
    }

    public Optional<Message> getMessage(int idMessage){
        return messageCrudRepository.findById(idMessage);
    }

    public Message save(Message mes) {
        return messageCrudRepository.save(mes);
    }

    public void delete(Message mes){
        messageCrudRepository.delete(mes);
    }
}
