package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.BookingDAO;
import com.kuba.shooting.range.management.model.Reservation;
import com.kuba.shooting.range.management.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private BookingDAO bookingDAO;

    public List<Reservation> getReservationListByDate(LocalDate localDate) {
        return this.bookingDAO.findAllByReservationDate(localDate);
    }
}
