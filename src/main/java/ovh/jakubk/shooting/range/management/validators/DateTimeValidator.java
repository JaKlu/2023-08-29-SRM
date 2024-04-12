package ovh.jakubk.shooting.range.management.validators;

import ovh.jakubk.shooting.range.management.exceptions.DateTimeValidationException;
import ovh.jakubk.shooting.range.management.exceptions.UserValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateTimeValidator {
    public static final LocalTime OPENING_HOUR = LocalTime.parse("10:00");
    public static final LocalTime CLOSING_HOUR = LocalTime.parse("18:00");

    public static void validateBookingDate(LocalDate localDate) {
        if (localDate.isBefore(LocalDate.now())) {
            throw new DateTimeValidationException();
        }
    }

    public static void validateBookingTime(LocalTime localTime) {
        if (!generateBookingHours().contains(localTime)) {
            throw new DateTimeValidationException();
        }
    }

    public static void validateBookingDateTime(LocalDate localDate, LocalTime localTime) {
        if (LocalDateTime.of(localDate, localTime).isBefore(LocalDateTime.now()) ||
                !generateBookingHours().contains(localTime)) {
            throw new DateTimeValidationException();
        }
    }

    public static List<LocalTime> generateBookingHours() {
        List<LocalTime> bookingHours = new ArrayList<>();
        LocalTime currentTime = DateTimeValidator.OPENING_HOUR;

        while (currentTime.isBefore(DateTimeValidator.CLOSING_HOUR)) {
            bookingHours.add(currentTime);
            currentTime = currentTime.plusHours(1);
        }
        return bookingHours;
    }
}
