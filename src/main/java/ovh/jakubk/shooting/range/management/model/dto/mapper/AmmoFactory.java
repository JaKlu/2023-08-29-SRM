package ovh.jakubk.shooting.range.management.model.dto.mapper;

import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoRequestDTO;

public class AmmoFactory {
    public static Ammo createAmmoFromAmmoRequestDTO(AmmoRequestDTO ammoRequestDTO) {
        return new Ammo(
                ammoRequestDTO.getId(),
                ammoRequestDTO.getGauge(),
                ammoRequestDTO.getQuantity()
        );
    }
}
