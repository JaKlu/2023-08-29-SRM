package com.kuba.shooting.range.management.services;

import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.dto.nowe.GunForArsenalViewResponseDTO;
import com.kuba.shooting.range.management.model.dto.nowe.GunListViewDTO;
import com.kuba.shooting.range.management.model.dto.nowe.GunManageViewDTO;
import com.kuba.shooting.range.management.model.rest.GunRequestDTO;
import com.kuba.shooting.range.management.model.rest.GunResponseDTO;
import com.kuba.shooting.range.management.model.rest.ResourceDeletedDTO;

import java.util.List;
import java.util.Optional;

public interface GunService {

    boolean existById(Long id);

    Optional<Gun> findById(Long id);

    GunResponseDTO findGunById(Long id);

    List<Gun> findAll();

    List<GunResponseDTO> findAllGuns();

    List<GunForArsenalViewResponseDTO> findAllForArsenalView();

    List<GunManageViewDTO> findAllForManageView();

    //void releaseGuns(GunCreationDto creationDTO);

    void releaseGuns(GunListViewDTO gunListViewDTO);

    GunResponseDTO releaseGun(Long id);

    //void takeGuns(GunCreationDto creationDTO);
    void takeGuns(GunListViewDTO gunListViewDTO);

    GunResponseDTO takeGun(Long id);

    Gun saveGun(Gun gun);


    GunResponseDTO saveGun(GunRequestDTO gunRequestDTO);

    GunResponseDTO updateGun(Long id, GunRequestDTO gunRequestDTO);

    ResourceDeletedDTO deleteGun(Long id);
}
