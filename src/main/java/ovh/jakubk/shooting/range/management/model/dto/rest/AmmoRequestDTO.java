package ovh.jakubk.shooting.range.management.model.dto.rest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AmmoRequestDTO {
    private Long id;
    @NotBlank
    private String gauge;
    @Min(value = 0)
    private int quantity;
}
