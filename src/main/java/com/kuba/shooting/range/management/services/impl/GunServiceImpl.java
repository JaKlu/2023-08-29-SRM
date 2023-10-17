package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.GunDAO;
import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.dto.GunAddDTO;
import com.kuba.shooting.range.management.model.dto.GunCreationDto;
import com.kuba.shooting.range.management.model.dto.GunListDTO;
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
    public Optional<Gun> findById(Long id) {
        return this.gunDAO.findById(id);
    }

    @Override
    public List<Gun> findAll() {
        return this.gunDAO.findAll();
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
    public void saveGun(Gun gun) {
        this.gunDAO.save(gun);
    }

    @Override
    public void saveGun(GunAddDTO gunAddDTO) {
        this.gunDAO.save(new Gun(gunAddDTO)); //TODO VALIDATORS
    }

    @Override
    public void deleteGun(Long id) {
        Optional<Gun> gunBox = this.findById(id);
        if (gunBox.isPresent() && (gunBox.get().isAvailable())) {
            this.gunDAO.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cannot delete gun that is not on stock.");
        }
    }
}
