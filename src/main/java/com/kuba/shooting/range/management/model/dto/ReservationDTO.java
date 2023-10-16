package com.kuba.shooting.range.management.model.dto;

import com.kuba.shooting.range.management.model.Reservation;
import com.kuba.shooting.range.management.model.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReservationDTO {
/*    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime reservationTime;

    private Long userId;*/

    private Reservation reservation;
    private boolean booked = false;

    public ReservationDTO(LocalDate localDate) {
        this.reservation = new Reservation(localDate);
    }

    public ReservationDTO(LocalDate localDate, LocalTime localTime) {
        this.reservation = new Reservation(localDate);
        this.getReservation().setReservationTime(localTime);
    }
}