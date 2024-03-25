package com.kuba.shooting.range.management.model.dto.nowe;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GunListViewDTO {
    private List<GunManageViewDTO> dtoList;
}
