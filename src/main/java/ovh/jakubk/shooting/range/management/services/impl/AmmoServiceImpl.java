package ovh.jakubk.shooting.range.management.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ovh.jakubk.shooting.range.management.database.dao.springdata.AmmoDAO;
import ovh.jakubk.shooting.range.management.exceptions.AmmoOnStockException;
import ovh.jakubk.shooting.range.management.exceptions.ResourceNotFoundException;
import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.mapper.AmmoFactory;
import ovh.jakubk.shooting.range.management.model.dto.mapper.AmmoMapper;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoRequestDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.ResourceDeletedDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.AmmoListViewDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.AmmoManageViewDTO;
import ovh.jakubk.shooting.range.management.services.AmmoService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AmmoServiceImpl implements AmmoService {
    private AmmoDAO ammoDAO;

    @Override
    public AmmoResponseDTO findAmmoById(Long id) {
        return this.ammoDAO.findById(id)
                .map(AmmoMapper::mapAmmoForAmmoResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Ammo not found"));
    }

    @Override
    public List<AmmoResponseDTO> findAllAmmo() {
        return this.ammoDAO.findAll()
                .stream()
                .map(AmmoMapper::mapAmmoForAmmoResponseDTO)
                .toList();
    }

    @Override
    public List<AmmoManageViewDTO> findAllForManageView() {
        return this.ammoDAO.findAll()
                .stream()
                .map(AmmoMapper::mapAmmoForAmmoManageViewDTO)
                .toList();
    }

    @Override
    public AmmoResponseDTO saveAmmo(AmmoRequestDTO ammoRequestDTO) {
        //TODO validation
        Ammo ammo = AmmoFactory.createAmmoFromAmmoRequestDTO(ammoRequestDTO);
        ammo.setId(0L);
        Ammo savedAmmo = this.ammoDAO.save(ammo);
        return AmmoMapper.mapAmmoForAmmoResponseDTO(savedAmmo);
    }

    @Override
    public AmmoResponseDTO updateAmmo(Long id, AmmoRequestDTO ammoRequestDTO) {
        //TODO validation
        Optional<Ammo> ammoBox = this.ammoDAO.findById(id);
        if (ammoBox.isEmpty()) throw new ResourceNotFoundException("Ammo not found");
        Ammo ammoToUpdate = AmmoFactory.createAmmoFromAmmoRequestDTO(ammoRequestDTO);
        ammoToUpdate.setId(id);
        ammoToUpdate.setQuantity(ammoBox.get().getQuantity());
        Ammo updatedAmmo = this.ammoDAO.save(ammoToUpdate);
        return AmmoMapper.mapAmmoForAmmoResponseDTO(updatedAmmo);
    }

    @Override
    public AmmoResponseDTO manageAmmo(Long id, Integer diff) {
        Optional<Ammo> ammoBox = this.ammoDAO.findById(id);
        if (ammoBox.isEmpty()) throw new ResourceNotFoundException("Ammo not found");
        Ammo ammoToManage = ammoBox.get();
        if (ammoToManage.getQuantity() + diff < 0) throw new AmmoOnStockException("Not enough ammo to manage");

        ammoToManage.setQuantity(ammoToManage.getQuantity() + diff);
        Ammo updatedAmmo = this.ammoDAO.save(ammoToManage);
        return AmmoMapper.mapAmmoForAmmoResponseDTO(updatedAmmo);
    }

    @Override
    public List<AmmoManageViewDTO> manageAmmoView(AmmoListViewDTO ammoListViewDTO, boolean supplying) {
        List<AmmoManageViewDTO> manageViewDTOList = new ArrayList<>();
        for (AmmoManageViewDTO ammoManageViewDTO : ammoListViewDTO.getDtoList()) {

            if (ammoManageViewDTO.getDiffInput() == 0) continue;
            manageViewDTOList.add(ammoManageViewDTO);
            if (!supplying) {
                this.manageAmmo(ammoManageViewDTO.getId(), -1 * ammoManageViewDTO.getDiffInput());
            } else this.manageAmmo(ammoManageViewDTO.getId(), ammoManageViewDTO.getDiffInput());
        }
        return manageViewDTOList;
    }

    @Override
    public ResourceDeletedDTO deleteAmmo(Long id) {
        Optional<Ammo> ammoBox = this.ammoDAO.findById(id);
        if (ammoBox.isEmpty()) throw new ResourceNotFoundException("Ammo not found");
        if (ammoBox.get().getQuantity() != 0) throw new AmmoOnStockException("Cannot delete ammo that is on stock.");
        this.ammoDAO.deleteById(id);
        return ResourceDeletedDTO.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .description("Deleted ammo with id: " + id)
                .build();
    }
}
