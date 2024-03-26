package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.GunDAO;
import com.kuba.shooting.range.management.exceptions.GunNotOnStockException;
import com.kuba.shooting.range.management.exceptions.ResourceNotFoundException;
import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.dto.mapper.GunFactory;
import com.kuba.shooting.range.management.model.dto.mapper.GunMapper;
import com.kuba.shooting.range.management.model.dto.nowe.GunForArsenalViewResponseDTO;
import com.kuba.shooting.range.management.model.dto.nowe.GunListViewDTO;
import com.kuba.shooting.range.management.model.dto.nowe.GunManageViewDTO;
import com.kuba.shooting.range.management.model.rest.GunRequestDTO;
import com.kuba.shooting.range.management.model.rest.GunResponseDTO;
import com.kuba.shooting.range.management.model.rest.ResourceDeletedDTO;
import com.kuba.shooting.range.management.services.GunService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GunServiceImpl implements GunService {
    private GunDAO gunDAO;

    @Override
    public boolean existById(Long id) {
        return this.gunDAO.existsById(id);
    }

    @Override
    public Optional<Gun> findById(Long id) {
        return this.gunDAO.findById(id);
    }

    @Override
    public GunResponseDTO findGunById(Long id) {
        return this.gunDAO.findById(id)
                .map(GunMapper::mapGunToGunResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Gun not found"));
    }

    @Override
    public List<Gun> findAll() {
        return this.gunDAO.findAll();
    }

    @Override
    public List<GunResponseDTO> findAllGuns() {
        return this.gunDAO.findAll()
                .stream()
                .map(GunMapper::mapGunToGunResponseDTO)
                .toList();
    }

    @Override
    public List<GunForArsenalViewResponseDTO> findAllForArsenalView() {
        return this.gunDAO.findAll()
                .stream()
                .map(GunMapper::mapGunToGunForArsenalViewResponseDTO)
                .toList();
    }

    @Override
    public List<GunManageViewDTO> findAllForManageView() {
        return this.gunDAO.findAll()
                .stream()
                .map(GunMapper::mapGunToGunManageViewDTO)
                .toList();
    }


    /*
        @Override
        public void releaseGuns(GunCreationDto creationDTO) {
            for (GunListDTO gunListDTO : creationDTO.getDtoList()) {
                if (!gunListDTO.isAction()) continue;
                Optional<Gun> gunBox = this.gunDAO.findById(gunListDTO.getId());
                if (gunBox.isPresent() &&
                        gunBox.get().isAvailable() &&
                        gunListDTO.isAction()) {
                    gunBox.get().setAvailable(false);
                    this.gunDAO.save(gunBox.get());
                }
            }
        }
    */

    //KLASA
    @Override
    public void releaseGuns(GunListViewDTO gunListViewDTO) {
        for (GunManageViewDTO gunManageViewDTO : gunListViewDTO.getDtoList()) {
            if (!gunManageViewDTO.isAction()) continue;
            this.releaseGun(gunManageViewDTO.getId());
        }
    }
    //REKORD
/*    @Override
    public void releaseGuns(GunListViewDTO gunListViewDTO) {
        for (GunManageViewDTO gunManageViewDTO : gunListViewDTO.getDtoList()) {
            if (!gunManageViewDTO.action()) continue;
            this.releaseGun(gunManageViewDTO.id());
        }
    }*/

    @Override
    public GunResponseDTO releaseGun(Long id) {
        Optional<Gun> gunBox = this.gunDAO.findById(id);
        if (gunBox.isEmpty()) throw new ResourceNotFoundException("Gun not found");
        if (!gunBox.get().isAvailable()) throw new GunNotOnStockException("Gun is already released.");
        gunBox.get().setAvailable(false);
        Gun savedGun = this.gunDAO.save(gunBox.get());
        return GunMapper.mapGunToGunResponseDTO(savedGun);
    }

/*
    @Override
    public void takeGuns(GunCreationDto creationDTO) {
        for (GunListDTO gunListDTO : creationDTO.getDtoList()) {
            if (!gunListDTO.isAction()) continue;
            Optional<Gun> gunBox = this.gunDAO.findById(gunListDTO.getId());
            if (gunBox.isPresent() &&
                    !gunBox.get().isAvailable() &&
                    gunListDTO.isAction()) {
                gunBox.get().setAvailable(true);
                this.gunDAO.save(gunBox.get());
            }
        }
    }
*/
    //KLASA
    @Override
    public void takeGuns(GunListViewDTO gunListViewDTO) {
        for (GunManageViewDTO gunManageViewDTO : gunListViewDTO.getDtoList()) {
            if (!gunManageViewDTO.isAction()) continue;
            this.takeGun(gunManageViewDTO.getId());
        }
    }
    //REKORD
/*    @Override
    public void takeGuns(GunListViewDTO gunListViewDTO) {
        for (GunManageViewDTO gunManageViewDTO : gunListViewDTO.getDtoList()) {
            if (!gunManageViewDTO.action()) continue;
            this.takeGun(gunManageViewDTO.id());
        }
    }*/

    @Override
    public GunResponseDTO takeGun(Long id) {
        Optional<Gun> gunBox = this.gunDAO.findById(id);
        if (gunBox.isEmpty()) throw new ResourceNotFoundException("Gun not found");
        if (gunBox.get().isAvailable()) throw new GunNotOnStockException("Gun is already taken.");
        gunBox.get().setAvailable(true);
        Gun savedGun = this.gunDAO.save(gunBox.get());
        return GunMapper.mapGunToGunResponseDTO(savedGun);
    }

    @Override
    public Gun saveGun(Gun gun) {
        return this.gunDAO.save(gun);
    }

    @Override
    public GunResponseDTO saveGun(GunRequestDTO gunRequestDTO) {
        //TODO validation
        Gun gun = GunFactory.createGunFromGunRequestDTO(gunRequestDTO);
        gun.setId(0L);
        Gun savedGun = this.gunDAO.save(gun);
        return GunMapper.mapGunToGunResponseDTO(savedGun);
    }


    @Override
    public GunResponseDTO updateGun(Long id, GunRequestDTO gunRequestDTO) {
        //TODO validation
        if (!this.existById(id)) throw new ResourceNotFoundException("Gun not found");
        Gun gun = GunFactory.createGunFromGunRequestDTO(gunRequestDTO);
        gun.setId(id);
        Gun updatedGun = this.gunDAO.save(gun);
        return GunMapper.mapGunToGunResponseDTO(updatedGun);
    }

    @Override
    public ResourceDeletedDTO deleteGun(Long id) {
        Optional<Gun> gunBox = this.findById(id);
        if (gunBox.isEmpty()) throw new ResourceNotFoundException("Gun not found");
        if (!gunBox.get().isAvailable()) throw new GunNotOnStockException("Cannot delete gun that is not on stock.");
        this.gunDAO.deleteById(id);
        return ResourceDeletedDTO.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .description("Deleted gun with id: " + id)
                .build();
    }
}
