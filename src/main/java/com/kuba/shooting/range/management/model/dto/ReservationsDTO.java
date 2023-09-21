package com.kuba.shooting.range.management.model.dto;

import com.kuba.shooting.range.management.model.Reservation;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReservationsDTO {
    private List<Reservation> reservations;
}
