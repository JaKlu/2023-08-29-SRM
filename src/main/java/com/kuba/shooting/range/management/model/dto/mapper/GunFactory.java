package com.kuba.shooting.range.management.model.dto.mapper;

import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.rest.GunRequestDTO;

public class GunFactory {
    public static Gun createGunFromGunRequestDTO(GunRequestDTO gunRequestDTO) {
        return new Gun(gunRequestDTO.getId(),
                gunRequestDTO.getCommonName(),
                gunRequestDTO.getBrand(),
                gunRequestDTO.getType(),
                gunRequestDTO.getGauge(),
                gunRequestDTO.getYearOfProd(),
                gunRequestDTO.getSerialNo(),
                gunRequestDTO.getCertificateNo(),
                gunRequestDTO.getDescription(),
                gunRequestDTO.isAvailable());
    }
}
