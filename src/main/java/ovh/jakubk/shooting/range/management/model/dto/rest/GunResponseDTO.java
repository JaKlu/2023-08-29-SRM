package ovh.jakubk.shooting.range.management.model.dto.rest;

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
}
