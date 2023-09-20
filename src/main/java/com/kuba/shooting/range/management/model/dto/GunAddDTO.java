package com.kuba.shooting.range.management.model.dto;

import com.kuba.shooting.range.management.model.Gun;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GunAddDTO {
    private Long id;
    private String commonName;
    private String brand;
    private String type;
    private String gauge;
    private String yearOfProd;
    private String serialNo;
    private String certificateNo;
    private String description;
    private boolean isAvailable = true;

    public GunAddDTO(Gun gun) {
        this.id = gun.getId();
        this.commonName = gun.getCommonName();
        this.brand = gun.getBrand();
        this.type = gun.getType();
        this.gauge = gun.getGauge();
        this.yearOfProd = String.valueOf(gun.getYearOfProd());
        this.serialNo = gun.getSerialNo();
        this.certificateNo = gun.getCertificateNo();
        this.description = gun.getDescription();
        this.isAvailable = gun.isAvailable();
    }
}
