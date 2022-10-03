package com.usa.boxproy.service;

import com.usa.boxproy.entities.Message;
import com.usa.boxproy.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }
    public Optional<Message> getMessage(int idMessage){
        return messageRepository.getMessage(idMessage);
    }
    public Message save(Message m){
        if (m.getIdMessage()==null){
            return messageRepository.save(m);
        }else{
            Optional<Message> mj = messageRepository.getMessage(m.getIdMessage());
            if (mj.isEmpty()){
                return messageRepository.save(m);
            }else{
                return m;
            }
        }
    }

    public Message update(Message m) {
        if (m.getIdMessage() != null) {
            Optional<Message> mj = messageRepository.getMessage(m.getIdMessage());
            if (!mj.isEmpty()) {
                    if (m.getIdMessage() != null) {
                    mj.get().setIdMessage(m.getIdMessage());
                }
                if (m.getMessageText() != null) {
                    mj.get().setMessageText(m.getMessageText());
                }

               messageRepository.save(mj.get());
                return mj.get();
            }else {
                return m;
            }
        }else {
            return m;
        }
    }

    public boolean delete(int idMessage){
        boolean flag=false;
        Optional<Message>m= messageRepository.getMessage(idMessage);
        if(m.isPresent()){
            messageRepository.delete(m.get());
            flag=true;
        }
        return flag;
    }
}
