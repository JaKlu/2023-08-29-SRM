package ovh.jakubk.shooting.range.management.services;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoRequestDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.ResourceDeletedDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.AmmoListViewDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.AmmoManageViewDTO;

import java.util.List;

public interface AmmoService {

    AmmoResponseDTO findAmmoById(Long id);

    List<AmmoResponseDTO> findAllAmmo();

    List<AmmoManageViewDTO> findAllForManageView();

    AmmoResponseDTO saveAmmo(AmmoRequestDTO ammoRequestDTO);

    AmmoResponseDTO updateAmmo(Long id, AmmoRequestDTO ammoRequestDTO);

    AmmoResponseDTO manageAmmo(Long id, Integer diff);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    List<AmmoManageViewDTO> manageAmmoView(AmmoListViewDTO ammoListViewDTO, boolean supplying);

    ResourceDeletedDTO deleteAmmo(Long id);
}
