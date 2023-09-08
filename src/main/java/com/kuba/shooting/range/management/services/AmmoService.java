package com.kuba.shooting.range.management.services;

import com.kuba.shooting.range.management.model.Ammo;
import com.kuba.shooting.range.management.model.dto.AmmoCreationDto;

import java.util.List;
import java.util.Optional;

public interface AmmoService {

    Optional<Ammo> findById(Long id);

    List<Ammo> findAll();

    void supplyAmmo(AmmoCreationDto creationDTO);

    void getAmmo(AmmoCreationDto creationDTO);
}
