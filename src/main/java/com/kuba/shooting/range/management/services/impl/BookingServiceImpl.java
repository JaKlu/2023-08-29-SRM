package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.BookingDAO;
import com.kuba.shooting.range.management.model.Reservation;
import com.kuba.shooting.range.management.model.dto.DayTemplate;
import com.kuba.shooting.range.management.model.dto.ReservationDTO;
import com.kuba.shooting.range.management.services.BookingService;
import com.kuba.shooting.range.management.validators.DateTimeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private BookingDAO bookingDAO;

    @Override
    public void save(Reservation reservation) {
        this.bookingDAO.save(reservation);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return this.bookingDAO.findById(id);
    }

    @Override
    public TreeMap<LocalDate, List<Reservation>> findAllByUserId(Long id) {
        List<Reservation> reservationList = this.bookingDAO.findAllByUserId(id);

        Map<LocalDate, List<Reservation>> collect = reservationList.stream()
                .collect(Collectors.groupingBy(Reservation::getReservationDate));

        return new TreeMap<>(collect);
    }

    @Override
    public Optional<Reservation> findByReservationDateAndReservationTime(LocalDate localDate, LocalTime localTime) {
        return this.bookingDAO.findByReservationDateAndReservationTime(localDate, localTime);
    }

    @Override
    public List<Reservation> getReservationListByDate(LocalDate localDate) {
        return this.bookingDAO.findAllByReservationDate(localDate);
    }

    @Override
    public DayTemplate getDayTemplate(LocalDate localDate) {
        final DayTemplate dayTemplate = new DayTemplate();
        dayTemplate.setDay(localDate);
        dayTemplate.setStartTime(DateTimeValidator.OPENING_HOUR);
        dayTemplate.setEndTime(DateTimeValidator.CLOSING_HOUR);

        LocalTime currentTime = dayTemplate.getStartTime();

        if (localDate.equals(LocalDate.now())) {
            currentTime = LocalTime.parse(LocalTime.now().plusHours(1)
                    .format(DateTimeFormatter.ofPattern("HH")) + ":00");
        }

        while (currentTime.isBefore(dayTemplate.getEndTime())) {
            ReservationDTO currentReservationDTO = new ReservationDTO(localDate);
            currentReservationDTO.getReservation().setReservationTime(currentTime);
            dayTemplate.getReservationDTOList().add(currentReservationDTO);
            currentTime = currentTime.plusHours(1);
        }

/*        DateTimeValidator.generateBookingHours().stream()
                .map(localTime -> dayTemplate.getReservationDTOList().add(new ReservationDTO(localDate, localTime)))
                .collect(Collectors.toList());*/

        List<Reservation> reservationList = this.getReservationListByDate(localDate);

        for (ReservationDTO reservationDTO : dayTemplate.getReservationDTOList()) {
            for (Reservation reservation : reservationList) {
                if (reservation.getReservationTime().equals(reservationDTO.getReservation().getReservationTime()) &&
                        !reservationDTO.isBooked()) {
                    reservationDTO.setReservation(reservation);
                    reservationDTO.setBooked(true);
                }
            }
        }
        for (ReservationDTO reservationDTO : dayTemplate.getReservationDTOList()) {
            if (!reservationDTO.isBooked()) {
                dayTemplate.setWholeDayReserved(false);
                break;
            } else dayTemplate.setWholeDayReserved(true);
        }
        return dayTemplate;
    }


    @Override
    public TreeMap<LocalDate, List<Reservation>> findAllByReservationDateFrom(LocalDate localDate) {
        localDate = localDate.minusDays(1);
        List<Reservation> reservationList = this.bookingDAO.findAllByReservationDateAfter(localDate);

        Map<LocalDate, List<Reservation>> collect = reservationList.stream()
                .collect(Collectors.groupingBy(Reservation::getReservationDate));

        return new TreeMap<>(collect);
    }

    @Override
    public void delete(Reservation reservation) {
        this.bookingDAO.delete(reservation);
    }
}
