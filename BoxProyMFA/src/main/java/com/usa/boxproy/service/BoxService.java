package com.usa.boxproy.service;

import com.usa.boxproy.entities.Box;
import com.usa.boxproy.repository.BoxRepository;
import com.usa.boxproy.repository.crudRepository.BoxCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoxService {

    @Autowired
    private BoxRepository boxRepository;

    public List<Box> getAll(){
        return boxRepository.getAll();
    }
    public Optional<Box> getBox(int id){
        return boxRepository.getBox(id);
    }
    public Box save(Box b){
        if (b.getId()==null){
            return boxRepository.save(b);
        }else{
            Optional<Box> c = boxRepository.getBox(b.getId());
            if (c.isEmpty()){
                return boxRepository.save(b);
            }else{
                return b;
            }
        }
    }
    public Box update(Box b) {
        if (b.getId() != null) {
            Optional<Box> d = boxRepository.getBox(b.getId());
            if (!d.isEmpty()) {
                if (b.getName() != null) {
                    d.get().setName(b.getName());
                }
                if (b.getLocation() != null) {
                    d.get().setLocation(b.getLocation());
                }
                if (b.getCapacity() != null) {
                    d.get().setCapacity(b.getCapacity());
                }
                if (b.getDescription() != null) {
                    d.get().setDescription(b.getDescription());
                }

                boxRepository.save(d.get());
                return d.get();
            }else {
                return b;
            }
        }else {
            return b;
        }
    }

    public boolean delete(int id){
        boolean flag=false;
        Optional<Box>b= boxRepository.getBox(id);
        if(b.isPresent()){
            boxRepository.delete(b.get());
            flag=true;
        }
        return flag;
    }
}
