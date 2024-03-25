package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.GunDAO;
import com.kuba.shooting.range.management.exceptions.GunNotOnStockException;
import com.kuba.shooting.range.management.exceptions.ResourceNotFoundException;
import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.dto.GunAddDTO;
import com.kuba.shooting.range.management.model.dto.GunCreationDto;
import com.kuba.shooting.range.management.model.dto.GunListDTO;
import com.kuba.shooting.range.management.model.dto.mapper.GunMapper;
import com.kuba.shooting.range.management.model.dto.nowe.GunForArsenalViewResponseDTO;
import com.kuba.shooting.range.management.model.dto.nowe.GunListViewDTO;
import com.kuba.shooting.range.management.model.dto.nowe.GunManageViewDTO;
import com.kuba.shooting.range.management.model.rest.GunResponseDTO;
import com.kuba.shooting.range.management.services.GunService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<Gun> findAll() {
        return this.gunDAO.findAll();
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

    @Override
    public void releaseGuns(GunListViewDTO gunListViewDTO) {

    }

    @Override
    public GunResponseDTO releaseGun(Long id) {
        Optional<Gun> gunBox = this.gunDAO.findById(id);
        if (gunBox.isEmpty()) throw new ResourceNotFoundException("Gun not found");
        if (!gunBox.get().isAvailable()) throw new GunNotOnStockException("Gun is already released.");
        gunBox.get().setAvailable(false);
        Gun savedGun = this.gunDAO.save(gunBox.get());
        return GunMapper.mapGunToGunResponseDTO(savedGun);
    }

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

    @Override
    public Gun saveGun(Gun gun) {
        return this.gunDAO.save(gun);
    }

    @Override
    public Gun saveGun(GunAddDTO gunAddDTO) {
        return this.gunDAO.save(new Gun(gunAddDTO)); //TODO VALIDATORS
    }

    @Override
    public void deleteGun(Long id) {
        Optional<Gun> gunBox = this.findById(id);
        if (gunBox.isPresent() && (gunBox.get().isAvailable())) {
            this.gunDAO.deleteById(id);
        } else {
            throw new GunNotOnStockException("Cannot delete gun that is not on stock.");
        }
    }
}
