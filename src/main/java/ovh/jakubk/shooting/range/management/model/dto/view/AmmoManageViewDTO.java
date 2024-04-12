package ovh.jakubk.shooting.range.management.model.dto.view;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmmoManageViewDTO {
    private Long id;
    private String gauge;
    private int quantity;
    @Min(value = 0)
    private int diffInput;
}
