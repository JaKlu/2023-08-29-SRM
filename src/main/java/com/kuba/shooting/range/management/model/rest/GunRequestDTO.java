package com.kuba.shooting.range.management.model.rest;

import lombok.Data;

@Data
public class GunRequestDTO {
    private Long id;
    private String commonName;
    private String brand;
    private String type;
    private String gauge;
    private int yearOfProd;
    private String serialNo;
    private String certificateNo;
    private String description;
}
