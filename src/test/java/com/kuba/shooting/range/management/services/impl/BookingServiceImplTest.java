package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.ShootingRangeManagementApplication;
import com.kuba.shooting.range.management.configuration.AppConfiguration;
import com.kuba.shooting.range.management.configuration.TestConfiguration;
import com.kuba.shooting.range.management.model.Reservation;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {ShootingRangeManagementApplication.class, AppConfiguration.class})
@ContextConfiguration(classes = {TestConfiguration.class})
class BookingServiceImplTest {
    @Autowired
    private BookingServiceImpl bookingService;

    @Test
    public void findAllByUserIdTest() {
        Long id = 5231L;
        TreeMap<LocalDate, List<Reservation>> reservations
                = this.bookingService.findAllByUserId(id);
    }
}
