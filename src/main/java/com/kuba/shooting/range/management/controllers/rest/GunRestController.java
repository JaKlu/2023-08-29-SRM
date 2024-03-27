package com.kuba.shooting.range.management.controllers.rest;

import com.kuba.shooting.range.management.model.dto.rest.GunRequestDTO;
import com.kuba.shooting.range.management.model.dto.rest.GunResponseDTO;
import com.kuba.shooting.range.management.model.dto.rest.ListResponse;
import com.kuba.shooting.range.management.model.dto.rest.ResourceDeletedDTO;
import com.kuba.shooting.range.management.services.GunService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/gun")
public class GunRestController {
    private GunService gunService;

    @GetMapping
    public ListResponse<GunResponseDTO> getAllGuns() {
        return new ListResponse<>(this.gunService.findAllGuns());
    }

    @PostMapping
    public ResponseEntity<GunResponseDTO> saveGun(@RequestBody GunRequestDTO gunRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.gunService.saveGun(gunRequestDTO));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GunResponseDTO> findGunById(@PathVariable Long id) {
        return ResponseEntity.ok(this.gunService.findGunById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<GunResponseDTO> updateGun(@PathVariable Long id,
                                                    @RequestBody GunRequestDTO gunRequestDTO) {
        return ResponseEntity.ok(this.gunService.updateGun(id, gunRequestDTO));
    }

    @PostMapping(path = "/release/{id}")
    public ResponseEntity<GunResponseDTO> releaseGun(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.gunService.releaseGun(id));
    }

    @PostMapping(path = "/take/{id}")
    public ResponseEntity<GunResponseDTO> takeGun(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.gunService.takeGun(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResourceDeletedDTO> deleteGun(@PathVariable Long id) {
        return ResponseEntity.ok(this.gunService.deleteGun(id));

    }
}
