package com.kuba.shooting.range.management.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DayTemplate {
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<ReservationDTO> reservationDTOList = new ArrayList<>();
    private boolean wholeDayReserved = false;
}
