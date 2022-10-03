package com.usa.boxproy.repository.crudRepository;

import com.usa.boxproy.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {
}
