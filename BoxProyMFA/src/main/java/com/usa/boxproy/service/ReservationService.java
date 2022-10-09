package com.usa.boxproy.service;


import com.usa.boxproy.entities.CountClient;
import com.usa.boxproy.entities.CountStatus;
import com.usa.boxproy.entities.Reservation;
import com.usa.boxproy.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public  List<CountClient> getTopClients(){
        return  reservationRepository.getTopClients();
    }

    public List<Reservation> getReservationPeriod(String dateA, String dateB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date a = new Date();
        Date b = new Date();

        try {
            a = parser.parse(dateA);
            b = parser.parse(dateB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (a.before(b)) {
            return reservationRepository.getReservationPeriod(a, b);
        } else {
            return new ArrayList<>();
        }
    }
    public CountStatus getReservationStatus(){
            List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
            List<Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");

            return new CountStatus((long) completed.size(), (long) cancelled.size());

        }

    }

