package com.kuba.shooting.range.management.model.dto;

import com.kuba.shooting.range.management.model.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReservationDTO {
    private Reservation reservation;
    private boolean booked = false;

    public ReservationDTO(LocalDate localDate){
        this.reservation = new Reservation(localDate);
    }

}
