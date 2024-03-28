package ovh.jakubk.shooting.range.management.services.impl;

import ovh.jakubk.shooting.range.management.database.dao.springdata.AmmoDAO;
import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.AmmoCreationDto;
import ovh.jakubk.shooting.range.management.model.dto.AmmoDTO;
import ovh.jakubk.shooting.range.management.services.AmmoService;
import ovh.jakubk.shooting.range.management.validators.AmmoValidator;
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
        AmmoValidator.validateSupplyInput(creationDTO);

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
        AmmoValidator.validateGetInput(creationDTO);

        for (AmmoDTO ammoDTO : creationDTO.getDtoList()) {
            if (ammoDTO.getDiff() == 0) continue;

            Optional<Ammo> ammoBox = this.ammoDAO.findById(ammoDTO.getId());
            if (ammoBox.isPresent()) {
                ammoBox.get().setQuantity(ammoBox.get().getQuantity() - ammoDTO.getDiff());
                this.ammoDAO.save(ammoBox.get());
            }
        }
    }

    @Override
    public void saveGauge(Ammo ammo) {
        this.ammoDAO.save(ammo);
    }

    @Override
    public void deleteGauge(Long id) {
        Optional<Ammo> ammoBox = this.findById(id);
        if (ammoBox.isPresent() && (ammoBox.get().getQuantity() == 0)) {
            this.ammoDAO.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
