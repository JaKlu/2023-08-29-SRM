package com.kuba.shooting.range.management.model.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class AmmoResponseDTO {
    private Long id;
    private String gauge;
    private int quantity;
}
