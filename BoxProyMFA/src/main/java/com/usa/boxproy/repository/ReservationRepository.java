package com.usa.boxproy.repository;


import com.usa.boxproy.entities.Client;
import com.usa.boxproy.entities.CountClient;
import com.usa.boxproy.entities.Reservation;
import com.usa.boxproy.repository.crudRepository.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
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

    //Reto 5
    public List<CountClient> getTopClients(){
        List<CountClient> respuesta = new ArrayList<>();
        List<Object[]> report=reservationCrudRepository.countTotalReservationByClient();
        for(int i=0; i < report.size(); i++){
            respuesta.add(new CountClient((Long)report.get(i)[1], (Client) report.get(i)[0]));
        }
        return respuesta;
    }

    public List<Reservation> getReservationPeriod(Date a, Date b){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(a,b);
    }
    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }


}
