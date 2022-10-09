package com.usa.boxproy.controller;

import com.usa.boxproy.entities.CountClient;
import com.usa.boxproy.entities.CountStatus;
import com.usa.boxproy.entities.Reservation;
import com.usa.boxproy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return  reservationService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int idReservation){
        return reservationService.getReservation(idReservation);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation res){
        return reservationService.save(res);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation){
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int idReservation){
        reservationService.delete(idReservation);
    }


    @GetMapping("/report-clients")
    public List<CountClient> getReportTopClients(){
        return reservationService.getTopClients();
    }

    @GetMapping("/report-dates/{dateA}/{dateB}")
    public List<Reservation> getReservationsDate(@PathVariable("dateA") String dateA,
                                                  @PathVariable("dateB") String dateB){
        return reservationService.getReservationPeriod(dateA,dateB);
    }
    @GetMapping("/report-status")
    public CountStatus getReportStatusReservations(){
        return reservationService.getReservationStatus();
    }
    /*
    @GetMapping("/report-dates/{inicial}/{final}")
    public List<Reservation> getReservationPeriod(@PathVariable("inicial") String ini,
                                                  @PathVariable("final") String fin){
        return reservationService.getReservationPeriod(ini,fin);
    }
    @GetMapping("/report-dates_total/{inicial}/{final}")
    public Integer getReservationPeriodTotal(@PathVariable("inicial") String ini,
                                             @PathVariable("final") String fin){
        return reservationService.getReservationPeriod(ini,fin).size();
    }

*/
}
