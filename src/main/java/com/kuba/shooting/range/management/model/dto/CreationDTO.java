/*
package com.kuba.shooting.range.management.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


// did not use generic class, because Thymeleaf do not recognize objects
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CreationDTO<T> {
    private List<T> dtoList = new ArrayList<>();

    public void addDTO(T t) {
        this.dtoList.add(t);
    }

    public CreationDTO(AmmoCreationDto ammoCreationDto) {
        this.dtoList = (List<T>) ammoCreationDto.getDtoList();
    }

    public CreationDTO(GunCreationDto gunCreationDto) {
        this.dtoList = (List<T>) gunCreationDto.getDtoList();
    }
}
*/
