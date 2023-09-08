package com.kuba.shooting.range.management.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AmmoCreationDto {
    private List<AmmoDTO> dtoList = new ArrayList<>();

    public void addDTO(AmmoDTO ammoDTO) {
        this.dtoList.add(ammoDTO);
    }
}