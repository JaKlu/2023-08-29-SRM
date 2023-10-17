package com.kuba.shooting.range.management.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.kuba.shooting.range.management.services",
        "com.kuba.shooting.range.management.session"
})
public class TestConfiguration {

}
