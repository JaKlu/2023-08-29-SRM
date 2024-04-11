package ovh.jakubk.shooting.range.management.model.dto.mapper;

import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.AmmoManageViewDTO;

public class AmmoMapper {
    public static AmmoResponseDTO mapAmmoForAmmoResponseDTO(Ammo ammo){
        return AmmoResponseDTO.builder()
                .id(ammo.getId())
                .gauge(ammo.getGauge())
                .quantity(ammo.getQuantity())
                .build();
    }

    public static AmmoManageViewDTO mapAmmoForAmmoManageViewDTO(Ammo ammo){
        return AmmoManageViewDTO.builder()
                .id(ammo.getId())
                .gauge(ammo.getGauge())
                .quantity(ammo.getQuantity())
                .build();
    }
}
