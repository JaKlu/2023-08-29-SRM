package com.kuba.shooting.range.management.services;

import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.dto.GunCreationDto;

import java.util.List;
import java.util.Optional;

public interface GunService {

    Optional<Gun> findById(Long id);

    List<Gun> findAll();

    void releaseGuns(GunCreationDto creationDTO);

    void takeGuns(GunCreationDto creationDTO);

    void saveGun(Gun gun);

    void deleteGun(Long id);
}
