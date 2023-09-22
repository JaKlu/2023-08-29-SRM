package com.kuba.shooting.range.management.database.dao.springdata;

import com.kuba.shooting.range.management.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByReservationDate(LocalDate localDate);

    List<Reservation> findAllByReservationDateAfter(LocalDate localDate);
}
