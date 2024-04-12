package ovh.jakubk.shooting.range.management.services;

import ovh.jakubk.shooting.range.management.model.dto.rest.GunRequestDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.GunResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.ResourceDeletedDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.GunForArsenalViewResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.GunListViewDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.GunManageViewDTO;

import java.util.List;

public interface GunService {

    boolean existById(Long id);

    GunResponseDTO findGunById(Long id);

    List<GunResponseDTO> findAllGuns();

    List<GunForArsenalViewResponseDTO> findAllForArsenalView();

    List<GunManageViewDTO> findAllForManageView();

    GunResponseDTO saveGun(GunRequestDTO gunRequestDTO);

    GunResponseDTO updateGun(Long id, GunRequestDTO gunRequestDTO);

    GunResponseDTO releaseGun(Long id);

    void releaseGuns(GunListViewDTO gunListViewDTO);

    GunResponseDTO takeGun(Long id);

    void takeGuns(GunListViewDTO gunListViewDTO);

    ResourceDeletedDTO deleteGun(Long id);
}
