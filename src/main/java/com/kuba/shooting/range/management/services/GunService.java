package com.kuba.shooting.range.management.services;

import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.dto.GunAddDTO;
import com.kuba.shooting.range.management.model.dto.GunCreationDto;
import com.kuba.shooting.range.management.model.dto.nowe.GunForArsenalViewResponseDTO;
import com.kuba.shooting.range.management.model.dto.nowe.GunListViewDTO;
import com.kuba.shooting.range.management.model.dto.nowe.GunManageViewDTO;
import com.kuba.shooting.range.management.model.rest.GunResponseDTO;

import java.util.List;
import java.util.Optional;

public interface GunService {

    boolean existById(Long id);

    Optional<Gun> findById(Long id);

    List<Gun> findAll();

    List<GunForArsenalViewResponseDTO> findAllForArsenalView();

    List<GunManageViewDTO> findAllForManageView();

    void releaseGuns(GunCreationDto creationDTO);

    void releaseGuns(GunListViewDTO gunListViewDTO);

    GunResponseDTO releaseGun(Long id);

    void takeGuns(GunCreationDto creationDTO);

    Gun saveGun(Gun gun);

    Gun saveGun(GunAddDTO gunAddDTO);

    void deleteGun(Long id);
}
