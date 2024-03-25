package com.kuba.shooting.range.management.model.dto.nowe;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GunManageViewDTO {
    private Long id;
    private String commonName;
    private boolean available;
    private boolean action;
}
