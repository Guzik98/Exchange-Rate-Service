package pl.gdyania.amw.exchange_rate.update;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

public class Schedule {
    @Configuration
    @EnableScheduling
    static
    class SchedulingConfiguration{
    }
}
