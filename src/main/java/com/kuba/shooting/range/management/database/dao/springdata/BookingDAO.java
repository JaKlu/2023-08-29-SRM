package com.kuba.shooting.range.management.database.dao.springdata;

import com.kuba.shooting.range.management.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDAO extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByReservationDateAndReservationTime(LocalDate localDate, LocalTime localTime);

    List<Reservation> findAllByUserId(Long id);

    List<Reservation> findAllByReservationDate(LocalDate localDate);

    List<Reservation> findAllByReservationDateAfter(LocalDate localDate);
}
