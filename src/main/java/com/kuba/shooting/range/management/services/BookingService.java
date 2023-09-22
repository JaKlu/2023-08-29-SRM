package com.kuba.shooting.range.management.services;

import com.kuba.shooting.range.management.model.Reservation;
import com.kuba.shooting.range.management.model.dto.DayTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingService {

    void save(Reservation reservation);

    Optional<Reservation> findById(Long id);

    List<Reservation> getReservationListByDate(LocalDate localDate);

    DayTemplate getDayTemplate(LocalDate localDate);

    Map<LocalDate, List<Reservation>> findAllByReservationDateFrom(LocalDate localDate);

    void delete(Reservation reservation);
}
