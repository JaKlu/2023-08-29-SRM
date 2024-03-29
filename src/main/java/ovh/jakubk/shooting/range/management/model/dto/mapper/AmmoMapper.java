package ovh.jakubk.shooting.range.management.model.dto.mapper;

import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoResponseDTO;

public class AmmoMapper {
    public static AmmoResponseDTO mapAmmoForAmmoResponseDTO(Ammo ammo){
        return AmmoResponseDTO.builder()
                .id(ammo.getId())
                .gauge(ammo.getGauge())
                .quantity(ammo.getQuantity())
                .build();
    }
}
