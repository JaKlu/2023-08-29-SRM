package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.AmmoDAO;
import com.kuba.shooting.range.management.model.Ammo;
import com.kuba.shooting.range.management.model.dto.AmmoCreationDto;
import com.kuba.shooting.range.management.model.dto.AmmoDTO;
import com.kuba.shooting.range.management.services.AmmoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AmmoServiceImpl implements AmmoService {

    private AmmoDAO ammoDAO;

    @Override
    public Optional<Ammo> findById(Long id) {
        return this.ammoDAO.findById(id);
    }

    @Override
    public List<Ammo> findAll() {
        return this.ammoDAO.findAll();
    }

    @Override
    public void supplyAmmo(AmmoCreationDto creationDTO) {
        for (AmmoDTO ammoDTO : creationDTO.getDtoList()) {
            if (ammoDTO.getDiff() == 0) continue;
            Optional<Ammo> ammoBox = this.ammoDAO.findById(ammoDTO.getId());
            if (ammoBox.isPresent()) {
                ammoBox.get().setQuantity(ammoBox.get().getQuantity() + ammoDTO.getDiff());
                this.ammoDAO.save(ammoBox.get());
            }
        }
    }

    @Override
    public void getAmmo(AmmoCreationDto creationDTO) {
        for (AmmoDTO ammoDTO : creationDTO.getDtoList()) {
            if (ammoDTO.getDiff() == 0) continue;
            Optional<Ammo> ammoBox = this.ammoDAO.findById(ammoDTO.getId());
            if (ammoBox.isPresent()) {
                ammoBox.get().setQuantity(ammoBox.get().getQuantity() - ammoDTO.getDiff());
                this.ammoDAO.save(ammoBox.get());
            }
        }
    }
}
