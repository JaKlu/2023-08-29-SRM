package ovh.jakubk.shooting.range.management.services;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoRequestDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.ResourceDeletedDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.AmmoListViewDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.AmmoManageViewDTO;

import java.util.List;
import java.util.Optional;

public interface AmmoService {

    Optional<Ammo> findById(Long id);

    AmmoResponseDTO findAmmoById(Long id);

    List<Ammo> findAll();

    List<AmmoResponseDTO> findAllAmmo();

    List<AmmoManageViewDTO> findAllForManageView();

    AmmoResponseDTO saveAmmo(AmmoRequestDTO ammoRequestDTO);

    AmmoResponseDTO updateAmmo(Long id, AmmoRequestDTO ammoRequestDTO);

    AmmoResponseDTO manageAmmo(Long id, Integer diff);

    @Transactional(
            propagation = Propagation.REQUIRES_NEW
    )
    void manageAmmoView(AmmoListViewDTO ammoListViewDTO, boolean supplying);

//    void supplyAmmo(AmmoCreationDto creationDTO);
//
//    void getAmmo(AmmoCreationDto creationDTO);

    void saveGauge(Ammo ammo);

    void deleteGauge(Long id);

    ResourceDeletedDTO deleteAmmo(Long id);
}
