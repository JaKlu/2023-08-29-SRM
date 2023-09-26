package com.kuba.shooting.range.management.services;

import com.kuba.shooting.range.management.model.Reservation;
import com.kuba.shooting.range.management.model.User;
import com.kuba.shooting.range.management.model.dto.DayTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public interface BookingService {

    void save(Reservation reservation);

    Optional<Reservation> findById(Long id);

    Optional<Reservation> findByReservationDateAndReservationTime(LocalDate localDate, LocalTime localTime);

    TreeMap<LocalDate, List<Reservation>> findAllByUserId(Long id);

    List<Reservation> getReservationListByDate(LocalDate localDate);

    DayTemplate getDayTemplate(LocalDate localDate);

    Map<LocalDate, List<Reservation>> findAllByReservationDateFrom(LocalDate localDate);

    void delete(Reservation reservation);
}
