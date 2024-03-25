package com.kuba.shooting.range.management.model.rest;

import com.kuba.shooting.range.management.model.Gun;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class GunResponseDTO {
    private Long id;
    private String commonName;
    private String brand;
    private String type;
    private String gauge;
    private int yearOfProd;
    private String serialNo;
    private String certificateNo;
    private String description;
    private boolean available;

    public GunResponseDTO(Gun gun) {
        this.id = gun.getId();
        this.commonName = gun.getCommonName();
        this.brand = gun.getBrand();
        this.type = gun.getType();
        this.gauge = gun.getGauge();
        this.yearOfProd = gun.getYearOfProd();
        this.serialNo = gun.getSerialNo();
        this.certificateNo = gun.getCertificateNo();
        this.description = gun.getDescription();
        this.available = gun.isAvailable();
    }
}
