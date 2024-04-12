package ovh.jakubk.shooting.range.management.services.impl;

import ovh.jakubk.shooting.range.management.configuration.TestConfiguration;
import ovh.jakubk.shooting.range.management.database.dao.springdata.AmmoDAO;
import ovh.jakubk.shooting.range.management.database.dao.springdata.BookingDAO;
import ovh.jakubk.shooting.range.management.database.dao.springdata.GunDAO;
import ovh.jakubk.shooting.range.management.database.dao.springdata.UserDAO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class ServiceGenericTest {
    @MockBean
    AmmoDAO ammoDAO;
    @MockBean
    BookingDAO bookingDAO;
    @MockBean
    GunDAO gunDAO;
    @MockBean
    UserDAO userDAO;
}
