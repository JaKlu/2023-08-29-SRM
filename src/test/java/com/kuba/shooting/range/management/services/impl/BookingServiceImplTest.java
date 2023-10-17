package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.configuration.TestConfiguration;
import com.kuba.shooting.range.management.database.dao.springdata.AmmoDAO;
import com.kuba.shooting.range.management.database.dao.springdata.BookingDAO;
import com.kuba.shooting.range.management.database.dao.springdata.GunDAO;
import com.kuba.shooting.range.management.database.dao.springdata.UserDAO;
import com.kuba.shooting.range.management.model.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class BookingServiceImplTest {
    @Autowired
    private BookingServiceImpl bookingService;

    @MockBean
    AmmoDAO ammoDAO;
    @MockBean
    BookingDAO bookingDAO;
    @MockBean
    GunDAO gunDAO;
    @MockBean
    UserDAO userDAO;

    @Test
    public void findAllByUserIdTest() {
        Long id = 5231L;
        TreeMap<LocalDate, List<Reservation>> reservations
                = this.bookingService.findAllByUserId(id);
    }
}
