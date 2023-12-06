package com.kuba.shooting.range.management.validators;

import com.kuba.shooting.range.management.model.dto.AmmoCreationDto;
import com.kuba.shooting.range.management.model.dto.AmmoDTO;

public class AmmoValidator {
    public static void validateGetInput(AmmoCreationDto creationDTO) {
        for (AmmoDTO ammoDTO : creationDTO.getDtoList()) {
            if (ammoDTO.getDiffInput().isEmpty()) ammoDTO.setDiffInput("0");
            ammoDTO.setDiff(Integer.parseInt(ammoDTO.getDiffInput()));
            if (ammoDTO.getDiff() < 0) throw new IllegalArgumentException();
            if (ammoDTO.getDiff() > ammoDTO.getQuantity()) throw new IllegalArgumentException();
        }
    }

    public static void validateSupplyInput(AmmoCreationDto creationDTO) {
        for (AmmoDTO ammoDTO : creationDTO.getDtoList()) {
            if (ammoDTO.getDiffInput().isEmpty()) ammoDTO.setDiffInput("0");
            ammoDTO.setDiff(Integer.parseInt(ammoDTO.getDiffInput()));
            if (ammoDTO.getDiff() < 0) throw new IllegalArgumentException();
        }
    }
}
