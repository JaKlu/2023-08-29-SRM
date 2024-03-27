package com.kuba.shooting.range.management.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//KLASA
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GunManageViewDTO {
    private Long id;
    private String commonName;
    private boolean available;
    private boolean action;
}

//REKORD
/*
@Builder
public record GunManageViewDTO(
        Long id,
        String commonName,
        boolean available,
        boolean action) {
}
*/
