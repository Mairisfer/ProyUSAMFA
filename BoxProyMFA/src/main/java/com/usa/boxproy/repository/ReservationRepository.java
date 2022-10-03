package com.usa.boxproy.repository;


import com.usa.boxproy.entities.Reservation;
import com.usa.boxproy.repository.crudRepository.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int idReservation){
        return reservationCrudRepository.findById(idReservation);
    }

    public Reservation save(Reservation res) {
        return reservationCrudRepository.save(res);
    }

    public void delete(Reservation res){
        reservationCrudRepository.delete(res);
    }
}
