package com.usa.boxproy.controller;

import com.usa.boxproy.entities.Client;
import com.usa.boxproy.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<Client> getAll(){
        return  clientService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Client> getBox(@PathVariable("id") int idClient){
        return clientService.getClient(idClient);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client cl){
        return clientService.save(cl);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client client){
        return clientService.update(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int idClient){
        clientService.delete(idClient);
    }

}
