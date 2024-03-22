package com.kuba.shooting.range.management.model.rest;

import lombok.Data;

@Data
public class GunViewRequestDTO {
    private GunResponseDTO gunResponseDTO;
    private boolean action = false;
}
