package ovh.jakubk.shooting.range.management.model.dto.mapper;

import ovh.jakubk.shooting.range.management.model.Gun;
import ovh.jakubk.shooting.range.management.model.dto.view.GunForArsenalViewResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.GunManageViewDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.GunResponseDTO;

public class GunMapper {
    public static GunForArsenalViewResponseDTO mapGunToGunForArsenalViewResponseDTO(Gun gun) {
        return GunForArsenalViewResponseDTO.builder()
                .commonName(gun.getCommonName())
                .type(gun.getType())
                .gauge(gun.getGauge())
                .description(gun.getDescription())
                .build();
    }

    public static GunManageViewDTO mapGunToGunManageViewDTO(Gun gun) {
        return GunManageViewDTO.builder()
                .id(gun.getId())
                .commonName(gun.getCommonName())
                .available(gun.isAvailable())
                .action(false)
                .build();
    }

    public static GunResponseDTO mapGunToGunResponseDTO(Gun gun) {
        return GunResponseDTO.builder()
                .id(gun.getId())
                .commonName(gun.getCommonName())
                .brand(gun.getBrand())
                .type(gun.getType())
                .gauge(gun.getGauge())
                .yearOfProd(gun.getYearOfProd())
                .serialNo(gun.getSerialNo())
                .certificateNo(gun.getCertificateNo())
                .description(gun.getDescription())
                .available(gun.isAvailable())
                .build();

    }
}
