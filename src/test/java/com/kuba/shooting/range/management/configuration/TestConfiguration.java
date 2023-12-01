package com.kuba.shooting.range.management.configuration;

import com.kuba.shooting.range.management.database.dao.springdata.AmmoDAO;
import com.kuba.shooting.range.management.database.dao.springdata.BookingDAO;
import com.kuba.shooting.range.management.database.dao.springdata.GunDAO;
import com.kuba.shooting.range.management.database.dao.springdata.UserDAO;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.kuba.shooting.range.management.services",
        "com.kuba.shooting.range.management.session"
})
public class TestConfiguration {

}
