package ovh.jakubk.shooting.range.management.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GunListViewDTO {
    private List<GunManageViewDTO> dtoList;
}