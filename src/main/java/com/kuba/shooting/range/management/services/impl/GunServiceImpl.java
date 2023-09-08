package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.GunDAO;
import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.dto.GunCreationDto;
import com.kuba.shooting.range.management.model.dto.GunDTO;
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
        for (GunDTO gunDTO : creationDTO.getDtoList()) {
            if (!gunDTO.isAction()) continue;
            Optional<Gun> gunBox = this.gunDAO.findById(gunDTO.getId());
            if (gunBox.isPresent() &&
                    gunBox.get().isAvailable() &&
                    gunDTO.isAction()) {
                gunBox.get().setAvailable(false);
                this.gunDAO.save(gunBox.get());
            }
        }
    }

    @Override
    public void takeGuns(GunCreationDto creationDTO) {
        for (GunDTO gunDTO : creationDTO.getDtoList()) {
            if (!gunDTO.isAction()) continue;
            Optional<Gun> gunBox = this.gunDAO.findById(gunDTO.getId());
            if (gunBox.isPresent() &&
                    !gunBox.get().isAvailable() &&
                    gunDTO.isAction()) {
                gunBox.get().setAvailable(true);
                this.gunDAO.save(gunBox.get());
            }
        }
    }
}
