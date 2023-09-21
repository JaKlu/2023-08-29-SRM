package com.kuba.shooting.range.management.services;

import com.kuba.shooting.range.management.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    List<Reservation> getReservationListByDate(LocalDate localDate);
}
