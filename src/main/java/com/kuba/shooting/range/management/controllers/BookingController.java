package com.kuba.shooting.range.management.controllers;

import com.kuba.shooting.range.management.controllers.utils.ModelUtils;
import com.kuba.shooting.range.management.model.Reservation;
import com.kuba.shooting.range.management.services.BookingService;
import com.kuba.shooting.range.management.session.SessionData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequestMapping(path = "/booking")
@Controller
public class BookingController {

    private SessionData sessionData;
    private BookingService bookingService;

    @GetMapping(path = "")
    public String booking(Model model,
                          @RequestParam(required = false) String formInfo,
                          @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        Reservation reservation = new Reservation();
        reservation.setReservationDate(LocalDate.now());

        model.addAttribute("reservationDate", LocalDate.now().toString());
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking";
    }

    @PostMapping(path = "")
    public String booking(Model model,
                          @RequestParam LocalDate reservationDate) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        System.out.println(reservationDate);

        LocalDate localDate = reservationDate;

        if (localDate.isBefore(LocalDate.now())) {
            localDate = LocalDate.now();
            this.sessionData.setFormError("Wybierz bieżącą lub przyszłą datę");
        }
        System.out.println(localDate);

        return "redirect:/booking/" + localDate;
    }

    @GetMapping(path = "/{reservationDate}")
    public String bookingDate(Model model,
                              @PathVariable LocalDate reservationDate,
                              @RequestParam(required = false) String formInfo,
                              @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
/*        LocalDate localDate;
        Reservation reservation = new Reservation();*/

/*        try {
            localDate = LocalDate.parse(date);
            reservation.setReservationDate(localDate);
        } catch (DateTimeParseException e) {
            this.sessionData.setFormError("Wybierz bieżącą lub przyszłą datę");
            return "redirect:/booking/";
        }*/

        List<Reservation> reservationList = this.bookingService.getReservationListByDate(reservationDate);
        System.out.println(reservationList);

        model.addAttribute("reservationDate", reservationDate);
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking";
    }


    @GetMapping(path = "/manage")
    public String bookingManage(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        return "redirect:/booking";
    }
}
