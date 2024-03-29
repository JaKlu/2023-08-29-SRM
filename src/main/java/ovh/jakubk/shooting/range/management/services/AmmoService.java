package ovh.jakubk.shooting.range.management.services;

import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.AmmoCreationDto;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoRequestDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.ResourceDeletedDTO;

import java.util.List;
import java.util.Optional;

public interface AmmoService {

    Optional<Ammo> findById(Long id);

    AmmoResponseDTO findAmmoById(Long id);

    List<Ammo> findAll();

    List<AmmoResponseDTO> findAllAmmo();

    AmmoResponseDTO saveAmmo(AmmoRequestDTO ammoRequestDTO);

    AmmoResponseDTO updateAmmo(Long id, AmmoRequestDTO ammoRequestDTO);

    void supplyAmmo(AmmoCreationDto creationDTO);

    void getAmmo(AmmoCreationDto creationDTO);

    void saveGauge(Ammo ammo);

    void deleteGauge(Long id);

    ResourceDeletedDTO deleteAmmo(Long id);
}
