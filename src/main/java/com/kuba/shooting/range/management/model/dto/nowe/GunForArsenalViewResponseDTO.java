package com.kuba.shooting.range.management.model.dto.nowe;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class GunForArsenalViewResponseDTO {
    private String commonName;
    private String type;
    private String gauge;
    private String description;
}
