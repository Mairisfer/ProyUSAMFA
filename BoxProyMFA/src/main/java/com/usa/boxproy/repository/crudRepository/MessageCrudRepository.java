package com.usa.boxproy.repository.crudRepository;


import com.usa.boxproy.entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
}
