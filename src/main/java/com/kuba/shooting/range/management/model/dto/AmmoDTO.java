package com.kuba.shooting.range.management.model.dto;

import com.kuba.shooting.range.management.model.Ammo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AmmoDTO {
    private Long id;

    private String gauge;

    private int quantity;

    private int diff;

    private String diffInput = "0";

    public AmmoDTO(Ammo ammo) {
        this.id = ammo.getId();
        this.gauge = ammo.getGauge();
        this.quantity = ammo.getQuantity();
    }
}
