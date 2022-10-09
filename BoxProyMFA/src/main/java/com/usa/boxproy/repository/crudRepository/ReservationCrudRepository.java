package com.usa.boxproy.repository.crudRepository;

import com.usa.boxproy.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {

    @Query ("select b.client, count(b.client) from Reservation as b group by b.client order by count(b.client) desc")
    public List<Object[]> countTotalReservationByClient();

    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date ini, Date fin);

    public List<Reservation> findAllByStatus(String status);

    /*@Query("select b.box, count(b.box) from Reservation as b group by b.box order by count(b.box) desc")
    public List<Object[]> countTotalReservationByBox();*/


}
