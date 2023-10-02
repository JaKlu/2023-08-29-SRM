package com.kuba.shooting.range.management.controllers;

import com.kuba.shooting.range.management.controllers.utils.ModelUtils;
import com.kuba.shooting.range.management.exceptions.DateTimeValidationException;
import com.kuba.shooting.range.management.model.Reservation;
import com.kuba.shooting.range.management.services.BookingService;
import com.kuba.shooting.range.management.session.SessionData;
import com.kuba.shooting.range.management.validators.DateTimeValidator;
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

    @GetMapping(path = {"", "/"})
    public String bookingHome() {
        return "redirect:/booking/date";
    }

    @GetMapping(path = {"/date"})
    public String booking(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        model.addAttribute("reservationDate", LocalDate.now().toString());
        model.addAttribute("today", LocalDate.now().toString());
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking";
    }

    @PostMapping(path = "/date")
    public String booking(Model model,
                          @RequestParam LocalDate reservationDate) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        try {
            DateTimeValidator.validateBookingDate(reservationDate);
        } catch (DateTimeValidationException e) {
            this.sessionData.setFormError("Wybierz poprawną datę");
            return "redirect:/booking/date";
        }
        return "redirect:/booking/date/" + reservationDate;
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

    @GetMapping(path = "/my-reservations/{id}")
    public String myReservation(Model model,
                                @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        Optional<Reservation> reservationBox = this.bookingService.findById(id);

        if (!(this.sessionData.isLogged() &&
                (reservationBox.isPresent() &&
                        this.sessionData.getUser().getId().equals(reservationBox.get().getUser().getId())))) {
            return "redirect:/";
        }

        model.addAttribute("reservation", reservationBox.get());
        model.addAttribute("state", "myReservations");
        return "booking-details";
    }

    @GetMapping(path = "/my-reservations/{id}/delete")
    public String deleteMyReservation(Model model,
                                      @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        Optional<Reservation> reservationBox = this.bookingService.findById(id);

        if (!(this.sessionData.isLogged() &&
                (reservationBox.isPresent() &&
                        this.sessionData.getUser().getId().equals(reservationBox.get().getUser().getId())))) {
            return "redirect:/";
        }

        this.bookingService.delete(reservationBox.get());
        return "redirect:/booking/my-reservations";
    }


    @GetMapping(path = {"/date/{date}"})
    public String bookingDate(Model model,
                              @PathVariable LocalDate date) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        try {
            DateTimeValidator.validateBookingDate(date);
        } catch (DateTimeValidationException e) {
            this.sessionData.setFormError("Wybierz poprawną datę");
            return "redirect:/booking/date";
        }

        model.addAttribute("reservationDate", date);
        model.addAttribute("today", LocalDate.now().toString());
        model.addAttribute("dayTemplate", this.bookingService.getDayTemplate(date));
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking";
    }


    @GetMapping(path = "/date/{date}/{time}")
    public String addReservation(Model model,
                                 @PathVariable LocalDate date,
                                 @PathVariable LocalTime time) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!sessionData.isLogged()) {
            return "redirect:/login";
        }

        try {
            DateTimeValidator.validateBookingDateTime(date, time);
        } catch (DateTimeValidationException e) {
            this.sessionData.setFormError("Wybierz poprawną rezerwację.");
            return "redirect:/booking/date";
        }

        Reservation reservation = new Reservation();
        reservation.setReservationDate(date);
        reservation.setReservationTime(time);
        Optional<Reservation> reservationBox = this.bookingService.findByReservationDateAndReservationTime(date, time);

        if (reservationBox.isPresent()) {
            this.sessionData.setFormError("Wybierz poprawną rezerwację.");
            return "redirect:/booking/date";
        }

        reservation.setUser(this.sessionData.getUser());

        model.addAttribute("reservation", reservation);
        model.addAttribute("today", LocalDate.now().toString());
        model.addAttribute("state", "add");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "booking-details";
    }

    @PostMapping(path = "/date/{date}/{time}")
    public String addReservation(Model model,
                                 @PathVariable LocalDate date,
                                 @PathVariable LocalTime time,
                                 @ModelAttribute Reservation reservation) {
        if (!sessionData.isLogged()) {
            return "redirect:/login";
        }
        try {
            DateTimeValidator.validateBookingDateTime(date, time);
        } catch (DateTimeValidationException e) {
            this.sessionData.setFormError("Wybierz poprawną rezerwację.");
            return "redirect:/booking/date";
        }

        if (this.bookingService.findByReservationDateAndReservationTime(date, time).isPresent()) {
            this.sessionData.setFormError("Wybierz poprawną rezerwację.");
            return "redirect:/booking/date";
        }
        reservation.setReservationDate(date);
        reservation.setReservationTime(time);
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

        model.addAttribute("today", LocalDate.now().toString());
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
