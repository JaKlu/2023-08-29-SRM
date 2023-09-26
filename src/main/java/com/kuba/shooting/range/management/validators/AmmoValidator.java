package com.kuba.shooting.range.management.validators;

import com.kuba.shooting.range.management.model.dto.AmmoCreationDto;
import com.kuba.shooting.range.management.model.dto.AmmoDTO;

public class AmmoValidator {
    public static void validateInput(AmmoCreationDto creationDTO){
        for (AmmoDTO ammoDTO : creationDTO.getDtoList()) {
            if (ammoDTO.getDiffInput().isEmpty()) ammoDTO.setDiffInput("0");
            ammoDTO.setDiff(Integer.parseInt(ammoDTO.getDiffInput()));
            if (ammoDTO.getDiff() < 0) throw new IllegalArgumentException();
        }
    }
}
