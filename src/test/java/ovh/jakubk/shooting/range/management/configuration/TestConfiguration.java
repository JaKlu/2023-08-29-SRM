package ovh.jakubk.shooting.range.management.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "ovh.jakubk.shooting.range.management.services",
        "ovh.jakubk.shooting.range.management.session"
})
public class TestConfiguration {

}
