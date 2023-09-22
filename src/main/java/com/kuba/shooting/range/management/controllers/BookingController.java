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
import java.time.LocalTime;
import java.util.Optional;

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

        model.addAttribute("reservationDate", LocalDate.now().toString());
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking";
    }

    @GetMapping(path = "/{date}/add/{time}")
    public String addReservation(Model model,
                                 @PathVariable LocalDate date,
                                 @PathVariable LocalTime time,
                                 @RequestParam(required = false) String formInfo,
                                 @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!sessionData.isLogged()) {
            return "redirect:/";
        }

        Reservation reservation = new Reservation();
        reservation.setReservationDate(date);
        reservation.setReservationTime(time);
        reservation.setUser(this.sessionData.getUser());

        model.addAttribute("reservation", reservation);
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking-details";
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

    @GetMapping(path = {"/{reservationDate}"})
    public String bookingDate(Model model,
                              @PathVariable LocalDate reservationDate,
                              @RequestParam(required = false) String formInfo,
                              @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        if (reservationDate == null) {
            reservationDate = LocalDate.now();
        }

        model.addAttribute("reservationDate", reservationDate);
        model.addAttribute("dayTemplate", this.bookingService.getDayTemplate(reservationDate));
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking";
    }


    /*    @GetMapping(path = "/manage")
        public String bookingManage(Model model) {
            ModelUtils.addCommonDataToModel(model, sessionData);
            if (!this.sessionData.isAdmin()) {
                return "redirect:/";
            }

            return "redirect:/booking";
        }*/
    @GetMapping(path = "/manage")
    public String manageReservations(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        System.out.println(this.bookingService.findAllByReservationDateFrom(LocalDate.now()));
        model.addAttribute("reservationMap", this.bookingService.findAllByReservationDateFrom(LocalDate.now()));
        return "booking-manage";
    }

    @GetMapping(path = "/manage/{id}")
    public String manageReservation(Model model,
                                    @PathVariable Long id,
                                    @RequestParam(required = false) String formInfo,
                                    @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        Optional<Reservation> reservationBox = this.bookingService.findById(id);
        if (reservationBox.isEmpty()) {
            this.sessionData.setFormError("Wybierz poprawną rezerwację.");
            return "redirect:/booking/manage";
        }

        model.addAttribute("reservation", reservationBox.get());
        return "booking-details";
    }

    @GetMapping(path = "/manage/{id}/delete")
    public String deleteReservation(Model model,
                                    @PathVariable Long id,
                                    @RequestParam(required = false) String formInfo,
                                    @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        Optional<Reservation> reservationBox = this.bookingService.findById(id);
        if (reservationBox.isEmpty()) {
            this.sessionData.setFormError("Wybierz poprawną rezerwację.");
            return "redirect:/booking/manage";
        }
        this.bookingService.delete(reservationBox.get());
        return "redirect:/booking/manage";
    }


}
