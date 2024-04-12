package ovh.jakubk.shooting.range.management.controllers.rest;

import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.rest.ListResponse;
import ovh.jakubk.shooting.range.management.services.AmmoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/ammo")
public class AmmoRestController {
    private AmmoService ammoService;

/*    @GetMapping
    public ListResponse<Ammo> findAll() {
        return new ListResponse<>(this.ammoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Ammo> save(@RequestBody Ammo ammo) {
        ammo.setId(0L);
        this.ammoService.saveAmmo(ammo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Ammo> findById(@PathVariable Long id) {
        return ResponseEntity.of(this.ammoService.findById(id));
    }*/


}
