package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.model.Reservation;
import com.kuba.shooting.range.management.model.User;
import com.kuba.shooting.range.management.model.dto.DayTemplate;
import com.kuba.shooting.range.management.model.dto.ReservationDTO;
import com.kuba.shooting.range.management.validators.DateTimeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


class BookingServiceImplTest extends ServiceGenericTest {
    @Autowired
    private BookingServiceImpl bookingService;

    @Test
    public void findAllByUserIdTest() {
        //given
        long id = 1L;
        Mockito.when(this.bookingDAO.findAllByUserId(id))
                .thenReturn(fakeReservationList());
        int expectedSizeDay1 = 1;
        int expectedSizeDay2 = 2;
        int expectedSizeDay3 = 3;

        //when
        TreeMap<LocalDate, List<Reservation>> reservations
                = this.bookingService.findAllByUserId(id);

        //then
        Assertions.assertEquals(expectedSizeDay1, reservations.get(
                LocalDate.of(2023, 1, 1)).size());
        Assertions.assertEquals(expectedSizeDay2, reservations.get(
                LocalDate.of(2023, 1, 2)).size());
        Assertions.assertEquals(expectedSizeDay3, reservations.get(
                LocalDate.of(2023, 1, 3)).size());
    }

    @Test
    public void findAllByReservationDateFrom() {
        //given
        LocalDate localDate = LocalDate.of(2023, 1, 1);
        LocalDate localDateMinus1 = localDate.minusDays(1);
        Mockito.when(this.bookingDAO.findAllByReservationDateAfter(localDateMinus1))
                .thenReturn(fakeReservationList());
        int expectedSizeDay1 = 1;
        int expectedSizeDay2 = 2;
        int expectedSizeDay3 = 3;

        //when
        TreeMap<LocalDate, List<Reservation>> reservations
                = this.bookingService.findAllByReservationDateFrom(localDate);

        //then
        Assertions.assertEquals(expectedSizeDay1, reservations.get(
                LocalDate.of(2023, 1, 1)).size());
        Assertions.assertEquals(expectedSizeDay2, reservations.get(
                LocalDate.of(2023, 1, 2)).size());
        Assertions.assertEquals(expectedSizeDay3, reservations.get(
                LocalDate.of(2023, 1, 3)).size());
    }

    private List<Reservation> fakeReservationList() {
        List<Reservation> list = new ArrayList<>();

        list.add(new Reservation(324L, LocalDate.of(2023, 1, 3),
                LocalTime.of(12, 0), new User()));
        list.add(new Reservation(8978L, LocalDate.of(2023, 1, 2),
                LocalTime.of(10, 0), new User()));
        list.add(new Reservation(345L, LocalDate.of(2023, 1, 3),
                LocalTime.of(10, 0), new User()));
        list.add(new Reservation(6798L, LocalDate.of(2023, 1, 1),
                LocalTime.of(10, 0), new User()));
        list.add(new Reservation(266L, LocalDate.of(2023, 1, 3),
                LocalTime.of(14, 0), new User()));
        list.add(new Reservation(58L, LocalDate.of(2023, 1, 2),
                LocalTime.of(12, 0), new User()));

        return list;
    }

    //getDayTemplate(LocalDate localDate)
    @Test
    public void getEmptyYesterdayDayTemplate() {
        //given
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<ReservationDTO> yesterdayList = List.of(
                new ReservationDTO(new Reservation(null, yesterday, LocalTime.of(10, 0), null), false),
                new ReservationDTO(new Reservation(null, yesterday, LocalTime.of(11, 0), null), false),
                new ReservationDTO(new Reservation(null, yesterday, LocalTime.of(12, 0), null), false),
                new ReservationDTO(new Reservation(null, yesterday, LocalTime.of(13, 0), null), false),
                new ReservationDTO(new Reservation(null, yesterday, LocalTime.of(14, 0), null), false),
                new ReservationDTO(new Reservation(null, yesterday, LocalTime.of(15, 0), null), false),
                new ReservationDTO(new Reservation(null, yesterday, LocalTime.of(16, 0), null), false),
                new ReservationDTO(new Reservation(null, yesterday, LocalTime.of(17, 0), null), false)
        );

        DayTemplate expectedDayTemplate = new DayTemplate(
                yesterday,
                LocalTime.of(10, 0),
                LocalTime.of(18, 0),
                yesterdayList,
                false);

        Mockito.when(this.bookingDAO.findAllByReservationDate(yesterday)).thenReturn(new ArrayList<>());

        //when
        DayTemplate yesterdayTemplate = this.bookingService.getDayTemplate(yesterday);

        //then
        Assertions.assertEquals(expectedDayTemplate, yesterdayTemplate);
    }
}