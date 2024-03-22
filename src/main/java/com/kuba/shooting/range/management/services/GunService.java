package com.kuba.shooting.range.management.services;

import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.dto.GunAddDTO;
import com.kuba.shooting.range.management.model.dto.GunCreationDto;

import java.util.List;
import java.util.Optional;

public interface GunService {

    boolean existById(Long id);

    Optional<Gun> findById(Long id);

    List<Gun> findAll();

    void releaseGuns(GunCreationDto creationDTO);

    void takeGuns(GunCreationDto creationDTO);

    Gun saveGun(Gun gun);

    Gun saveGun(GunAddDTO gunAddDTO);

    void deleteGun(Long id);
}
