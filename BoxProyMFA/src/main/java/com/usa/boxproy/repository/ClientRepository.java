package com.usa.boxproy.repository;



import com.usa.boxproy.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import com.usa.boxproy.repository.crudRepository.ClientCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    @Autowired
    private ClientCrudRepository clientCrudRepository;

    public List<Client> getAll(){
        return (List<Client>) clientCrudRepository.findAll();
    }

    public Optional<Client> getClient (int idClient){
        return clientCrudRepository.findById(idClient);
    }

    public Client save(Client cl) {
        return clientCrudRepository.save(cl);
    }

    public void delete(Client cl){
        clientCrudRepository.delete(cl);
    }
}
