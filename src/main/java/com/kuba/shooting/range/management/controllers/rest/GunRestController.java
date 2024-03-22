package com.kuba.shooting.range.management.controllers.rest;

import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.rest.GunResponseDTO;
import com.kuba.shooting.range.management.model.rest.ListResponse;
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
    public ListResponse<GunResponseDTO> getGuns() {
        return new ListResponse<>(this.gunService.findAll().stream()
                .map(GunResponseDTO::new).toList());
    }

    @PostMapping
    public ResponseEntity<Gun> saveGun(@RequestBody Gun gun) {
        gun.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.gunService.saveGun(gun));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Gun> findGunById(@PathVariable Long id) {
        return ResponseEntity.of(this.gunService.findById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Gun> updateGun(@PathVariable Long id,
                                         @RequestBody Gun gun) {
        gun.setId(id);
        if (!this.gunService.existById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.gunService.saveGun(gun));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteGun(@PathVariable Long id) {
        this.gunService.deleteGun(id);
    }

}
