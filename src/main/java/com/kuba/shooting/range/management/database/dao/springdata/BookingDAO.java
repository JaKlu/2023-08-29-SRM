package com.kuba.shooting.range.management.database.dao.springdata;

import com.kuba.shooting.range.management.model.Reservation;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface BookingDAO extends ListCrudRepository<Reservation, Long> {
    List<Reservation> findAllByReservationDate(LocalDate localDate);
}
