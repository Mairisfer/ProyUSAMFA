package com.usa.boxproy.repository;


;
import com.usa.boxproy.entities.Admin;
import com.usa.boxproy.entities.Score;
import com.usa.boxproy.repository.crudRepository.AdminCrudRepository;
import com.usa.boxproy.repository.crudRepository.ScoreCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {

    @Autowired
    private AdminCrudRepository adminCrudRepository;

    public List<Admin> getAll(){
        return (List<Admin>) adminCrudRepository.findAll();
    }

    public Optional<Admin> getAdmin(int idAdmin){return adminCrudRepository.findById(idAdmin);
    }

    public Admin save(Admin a) {
        return adminCrudRepository.save(a);
    }

    public void delete(Admin a){
        adminCrudRepository.delete(a);
    }
}

