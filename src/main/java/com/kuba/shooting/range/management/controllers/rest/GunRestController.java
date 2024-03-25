package com.kuba.shooting.range.management.controllers.rest;

import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.rest.GunRequestDTO;
import com.kuba.shooting.range.management.model.rest.GunResponseDTO;
import com.kuba.shooting.range.management.model.rest.ListResponse;
import com.kuba.shooting.range.management.services.GunService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    //    @PostMapping
//    public ResponseEntity<Gun> saveGun(@RequestBody Gun gun) {
//        gun.setId(0L);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(this.gunService.saveGun(gun));
//    }
    @PostMapping
    public ResponseEntity<GunResponseDTO> saveGun(@RequestBody GunRequestDTO gunRequestDTO) {
        gunRequestDTO.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new GunResponseDTO(this.gunService.saveGun(new Gun(gunRequestDTO))));
    }

    /*
        @GetMapping(path = "/{id}")
        public ResponseEntity<Gun> findGunById(@PathVariable Long id) {
            return ResponseEntity.of(this.gunService.findById(id));
        }
    */
    @GetMapping(path = "/{id}")
    public ResponseEntity<GunResponseDTO> findGunById(@PathVariable Long id) {
        Optional<Gun> gunBox = this.gunService.findById(id);
        return gunBox.map(gun -> ResponseEntity.ok(new GunResponseDTO(gun)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
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
    */
    @PutMapping(path = "/{id}")
    public ResponseEntity<GunResponseDTO> updateGun(@PathVariable Long id,
                                                    @RequestBody GunRequestDTO gunRequestDTO) {
        gunRequestDTO.setId(id);
        Optional<Gun> gunBox = this.gunService.findById(id);
        if (gunBox.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GunResponseDTO(this.gunService.saveGun(new Gun(gunRequestDTO))));
    }

    /*
        @DeleteMapping(path = "/{id}")
        public ResponseEntity<String> deleteGun(@PathVariable Long id) {
            if (!(this.gunService.existById(id))){
                return ResponseEntity.notFound().build();
            }
            if (true){
                this.gunService.deleteGun(id);
            }

        }
    */
    @PostMapping(path = "/release/{id}")
    public ResponseEntity<GunResponseDTO> releaseGun(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.gunService.releaseGun(id));
    }
}
