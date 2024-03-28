package ovh.jakubk.shooting.range.management.controllers.rest;

import ovh.jakubk.shooting.range.management.model.Reservation;
import ovh.jakubk.shooting.range.management.model.User;
import ovh.jakubk.shooting.range.management.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/booking")
public class BookingRestController {
    private BookingService bookingService;

/*    @GetMapping
    public ReservationListResponse findReservationsByUserId(@RequestParam Long userId){
        this.bookingService.findAllByUserId(userId)
    }*/

    @GetMapping(path = "/{id}")
    public ResponseEntity<Reservation> findReservationById(@PathVariable Long id) {
        User user = new User();
        return ResponseEntity.of(this.bookingService.findById(id));
    }


}
