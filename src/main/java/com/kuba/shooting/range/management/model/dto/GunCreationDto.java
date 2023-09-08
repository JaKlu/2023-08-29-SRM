package com.kuba.shooting.range.management.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class GunCreationDto {
    private List<GunDTO> dtoList = new ArrayList<>();

    public void addDTO(GunDTO gunDTO) {
        this.dtoList.add(gunDTO);
    }
}
