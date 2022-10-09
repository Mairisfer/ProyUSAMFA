package com.usa.boxproy.service;

import com.usa.boxproy.entities.Admin;
import com.usa.boxproy.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.getAll();
    }
    public Optional<Admin> getAdmin(int idAdmin){
        return adminRepository.getAdmin(idAdmin);
    }
    public Admin save(Admin ad){
        if (ad.getIdAdmin()==null){
            return adminRepository.save(ad);
        }else{
            Optional<Admin> a = adminRepository.getAdmin(ad.getIdAdmin());
            if (a.isEmpty()){
                return adminRepository.save(ad);
            }else{
                return ad;
            }
        }
    }
    public Admin update(Admin ad) {
        if (ad.getIdAdmin() != null) {
            Optional<Admin> am = adminRepository.getAdmin(ad.getIdAdmin());
            if (!am.isEmpty()) {
                if (ad.getEmail() != null) {
                    am.get().setEmail(ad.getEmail());
                }
                if (ad.getPassword() != null) {
                    am.get().setPassword(ad.getPassword());
                }
                if (ad.getName() != null) {
                    am.get().setName(ad.getName());
                }

                adminRepository.save(am.get());
                return am.get();
            }else {
                return ad;
            }
        }else {
            return ad;
        }
    }

    public boolean delete(int idAdmin){
        boolean flag=false;
        Optional<Admin>ad= adminRepository.getAdmin(idAdmin);
        if(ad.isPresent()){
            adminRepository.delete(ad.get());
            flag=true;
        }
        return flag;
    }
}
