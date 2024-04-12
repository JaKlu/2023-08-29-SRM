package ovh.jakubk.shooting.range.management.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GunManageViewDTO {
    private Long id;
    private String commonName;
    private boolean available;
    private boolean action;
}