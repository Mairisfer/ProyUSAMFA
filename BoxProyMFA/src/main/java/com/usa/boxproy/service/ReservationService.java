package com.usa.boxproy.service;


import com.usa.boxproy.entities.Reservation;
import com.usa.boxproy.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    public Optional<Reservation> getReservation(int idReservation){
        return reservationRepository.getReservation(idReservation);
    }
    public Reservation save(Reservation r){
        if (r.getIdReservation()==null){
            return reservationRepository.save(r);
        }else{
            Optional<Reservation> rs = reservationRepository.getReservation(r.getIdReservation());
            if (rs.isEmpty()){
                return reservationRepository.save(r);
            }else{
                return r;
            }
        }
    }
    public Reservation update(Reservation r) {
        if (r.getIdReservation() != null) {
            Optional<Reservation> rs = reservationRepository.getReservation(r.getIdReservation());
            if (!rs.isEmpty()) {

                if (r.getIdReservation() != null) {
                    rs.get().setIdReservation(r.getIdReservation());
                }
                if (r.getStartDate() != null) {
                    rs.get().setStartDate(r.getStartDate());
                }
                if (r.getDevolutionDate() != null){
                    rs.get().setDevolutionDate(r.getDevolutionDate());
                }
                if (r.getStatus() != null){
                    rs.get().setStatus(r.getStatus());
                }

                reservationRepository.save(rs.get());
                return rs.get();
            }else {
                return r;
            }
        }else {
            return r;
        }
    }

    public boolean delete(int idReservation){
        boolean flag=false;
        Optional<Reservation>r= reservationRepository.getReservation(idReservation);
        if(r.isPresent()){
            reservationRepository.delete(r.get());
            flag=true;
        }
        return flag;
    }
}
