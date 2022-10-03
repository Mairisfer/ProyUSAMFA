package com.usa.boxproy.service;

import com.usa.boxproy.entities.Client;
import com.usa.boxproy.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    public Optional<Client> getClient(int idClient){
        return clientRepository.getClient(idClient);
    }
    public Client save(Client cl){
        if (cl.getIdClient()==null){
            return clientRepository.save(cl);
        }else{
            Optional<Client> ct = clientRepository.getClient(cl.getIdClient());
            if (ct.isEmpty()){
                return clientRepository.save(cl);
            }else{
                return cl;
            }
        }
    }
    public Client update(Client cl) {
        if (cl.getIdClient() != null) {
            Optional<Client> ci = clientRepository.getClient(cl.getIdClient());
            if (!ci.isEmpty()) {
                if (cl.getName() != null) {
                    ci.get().setName(cl.getName());
                }
                if (cl.getEmail() != null) {
                    ci.get().setEmail(cl.getEmail());
                }
                if (cl.getAge() != null) {
                    ci.get().setAge(cl.getAge());
                }
                if (cl.getPassword() != null) {
                    ci.get().setPassword(cl.getPassword());
                }
                clientRepository.save(ci.get());
                return ci.get();
            }else {
                return cl;
            }
        }else {
            return cl;
        }
    }

    public boolean delete(int idClient){
        boolean flag=false;
        Optional<Client>cl= clientRepository.getClient(idClient);
        if(cl.isPresent()){
            clientRepository.delete(cl.get());
            flag=true;
        }
        return flag;
    }
}
