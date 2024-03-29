package ovh.jakubk.shooting.range.management.model.dto.rest;

import lombok.Data;

@Data
public class AmmoRequestDTO {
    private Long id;
    private String gauge;
    private int quantity;
}
