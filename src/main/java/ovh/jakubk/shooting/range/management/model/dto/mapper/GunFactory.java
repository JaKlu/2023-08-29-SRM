package ovh.jakubk.shooting.range.management.model.dto.mapper;

import ovh.jakubk.shooting.range.management.model.Gun;
import ovh.jakubk.shooting.range.management.model.dto.rest.GunRequestDTO;

public class GunFactory {
    public static Gun createGunFromGunRequestDTO(GunRequestDTO gunRequestDTO) {
        return new Gun(
                gunRequestDTO.getId(),
                gunRequestDTO.getCommonName(),
                gunRequestDTO.getBrand(),
                gunRequestDTO.getType(),
                gunRequestDTO.getGauge(),
                gunRequestDTO.getYearOfProd(),
                gunRequestDTO.getSerialNo(),
                gunRequestDTO.getCertificateNo(),
                gunRequestDTO.getDescription(),
                gunRequestDTO.isAvailable()
        );
    }
}
