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
    public String booking(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);


        model.addAttribute("reservationDate", LocalDate.now().toString());

        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking";
    }

    @PostMapping(path = "")
    public String booking(Model model,
                          @RequestParam LocalDate reservationDate) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        /*System.out.println(reservationDate);*/
/*
        LocalDate localDate = reservationDate;*/

/*        if (localDate.isBefore(LocalDate.now())) {
            localDate = LocalDate.now();
            this.sessionData.setFormError("Wybierz bieżącą lub przyszłą datę");
        }*/
        /*        System.out.println(localDate);*/

        return "redirect:/booking/" + reservationDate;
    }

    @GetMapping(path = "/my-reservations")
    public String myReservations(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isLogged()) {
            return "redirect:/";
        }
        System.out.println(this.bookingService.findAllByUserId(this.sessionData.getUser().getId()));

        model.addAttribute("reservationMap", this.bookingService.findAllByUserId(this.sessionData.getUser().getId()));
        model.addAttribute("state", "myReservations");
        return "booking-manage";
    }

    @GetMapping(path = {"/{date}"})
    public String bookingDate(Model model,
                              @PathVariable LocalDate date) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        if (date == null || date.isBefore(LocalDate.now())) {
            this.sessionData.setFormError("Wybierz poprawną datę");
            return "redirect:/booking";
        }

        model.addAttribute("reservationDate", date);
        model.addAttribute("dayTemplate", this.bookingService.getDayTemplate(date));
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking";
    }


    @GetMapping(path = "/{date}/{time}")
    public String addReservation(Model model,
                                 @PathVariable LocalDate date,
                                 @PathVariable LocalTime time) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!sessionData.isLogged()) {
            return "redirect:/login";
        }

        Reservation reservation = new Reservation();
        reservation.setReservationDate(date);
        reservation.setReservationTime(time);
        Optional<Reservation> reservationBox = this.bookingService.findByReservationDateAndReservationTime(date, time);

        if (reservationBox.isPresent()) {
            return "redirect:/booking";
        }

        reservation.setUser(this.sessionData.getUser());

        model.addAttribute("reservation", reservation);
        model.addAttribute("state", "add");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking-details";
    }

    @PostMapping(path = "/{date}/{time}")
    public String addReservation(Model model,
                                 @ModelAttribute Reservation reservation) {
        if (!sessionData.isLogged()) {
            return "redirect:/login";
        }
        reservation.setUser(this.sessionData.getUser());

        this.bookingService.save(reservation);

        System.out.println(reservation);
        return "redirect:/booking/my-reservations";
    }

    @GetMapping(path = "/manage")
    public String manageReservations(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

        System.out.println(this.bookingService.findAllByReservationDateFrom(LocalDate.now()));
        model.addAttribute("reservationMap", this.bookingService.findAllByReservationDateFrom(LocalDate.now()));
        model.addAttribute("state", "manage");
        return "booking-manage";
    }

    @GetMapping(path = "/manage/{id}")
    public String manageReservation(Model model,
                                    @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        Optional<Reservation> reservationBox = this.bookingService.findById(id);

        if (!(this.sessionData.isAdminOrEmployee() ||
                (reservationBox.isPresent()) &&
                        this.sessionData.getUser().getId().equals(reservationBox.get().getUser().getId()))) {
            return "redirect:/";
        }

        if (reservationBox.isEmpty() && this.sessionData.isAdminOrEmployee()) {
            this.sessionData.setFormError("Wybierz poprawną rezerwację.");
            return "redirect:/booking/manage";
        }

        model.addAttribute("reservation", reservationBox.get());
        model.addAttribute("state", "manage");
        return "booking-details";
    }

    @GetMapping(path = "/manage/{id}/delete")
    public String deleteReservation(Model model,
                                    @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        Optional<Reservation> reservationBox = this.bookingService.findById(id);
        if (!this.sessionData.isLogged()) {
            return "redirect:/";
        }

        if (!((this.sessionData.isAdminOrEmployee()) ||
                (reservationBox.isPresent()) &&
                        this.sessionData.getUser().getId().equals(reservationBox.get().getUser().getId()))) {
            return "redirect:/";
        }

        if (reservationBox.isEmpty()) {
            this.sessionData.setFormError("Wybierz poprawną rezerwację.");
            return "redirect:/booking/manage";
        }
        this.bookingService.delete(reservationBox.get());
        return "redirect:/booking/manage";
    }
}
