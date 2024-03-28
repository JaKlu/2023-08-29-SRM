package ovh.jakubk.shooting.range.management.model.dto;

import ovh.jakubk.shooting.range.management.model.Ammo;
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
    private String diffInput = "";

    public AmmoDTO(Ammo ammo) {
        this.id = ammo.getId();
        this.gauge = ammo.getGauge();
        this.quantity = ammo.getQuantity();
    }
}
