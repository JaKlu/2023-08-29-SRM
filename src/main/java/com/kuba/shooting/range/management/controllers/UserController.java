package com.kuba.shooting.range.management.controllers;

import com.kuba.shooting.range.management.controllers.utils.ModelUtils;
import com.kuba.shooting.range.management.exceptions.LoginAlreadyExistException;
import com.kuba.shooting.range.management.exceptions.UserValidationException;
import com.kuba.shooting.range.management.model.User;
import com.kuba.shooting.range.management.model.dto.ChangePassDTO;
import com.kuba.shooting.range.management.services.BookingService;
import com.kuba.shooting.range.management.services.UserService;
import com.kuba.shooting.range.management.session.SessionData;
import com.kuba.shooting.range.management.validators.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/users")
public class UserController {

    private SessionData sessionData;
    private final UserService userService;
    private final BookingService bookingService;

    @GetMapping(path = "/manage")
    public String users(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        model.addAttribute("userList", this.userService.findAll());
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());

        return "users";
    }

    @GetMapping(path = "/register")
    public String register(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("userModel", new User());
        model.addAttribute("state", "register");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "register";
    }

    @PostMapping(path = "/register")
    public String register(@ModelAttribute User user,
                           @RequestParam String password2) {
        try {
            UserValidator.validateNewUser(user);
            UserValidator.validatePasswordEquality(user.getPassword(), password2);
            this.userService.save(user);
        } catch (LoginAlreadyExistException e) {
            this.sessionData.setFormError("Użytkownik " + user.getLogin() + " już istnieje");
            e.printStackTrace();
            return "redirect:/users/register";
        } catch (UserValidationException e) {
            this.sessionData.setFormError("Wprowadź poprawne dane do formularza");
            e.printStackTrace();
            return "redirect:/users/register";
        }
        this.sessionData.setFormInfo("Rejestracja zakończona sukcesem. Zaloguj się na swoje konto.");
        return "redirect:/login";
    }


    @GetMapping(path = "/my-profile")
    public String myProfile(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        if (!this.sessionData.isLogged()) {
            return "redirect:/";
        }
        Optional<User> userBox = this.userService.findById(sessionData.getUser().getId());
        if (userBox.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("userModel", userBox.get());
        model.addAttribute("state", "edit");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "register";
    }

    @PostMapping(path = "/my-profile")
    public String myProfile(@ModelAttribute User user) {
        if (!this.sessionData.isLogged()) {
            return "redirect:/";
        }

        try {
            UserValidator.validateEditedUser(user);
            Optional<User> userBox = this.userService.findById(sessionData.getUser().getId());
            if (userBox.isEmpty()) {
                return "redirect:/";
            }
            user.setId(sessionData.getUser().getId());
            user.setLogin(userBox.get().getLogin());
            user.setPassword(userBox.get().getPassword());
            user.getAddress().setId(userBox.get().getAddress().getId());
            if (!this.sessionData.isAdmin()) {
                user.setRole(userBox.get().getRole());
            }
            this.userService.update(user);
        } catch (UserValidationException e) {
            this.sessionData.setFormError("Wprowadź poprawne dane do formularza");
            e.printStackTrace();
            return "redirect:/users/my-profile";
        }
        this.sessionData.setFormInfo("Edycja zakończona sukcesem.");
        return "redirect:/users/my-profile";
    }

    @GetMapping(path = "/manage/{id}/reservations")
    public String myReservations(Model model,
                                 @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!(this.sessionData.isAdminOrEmployee() ||
                (this.sessionData.getUser().getId().equals(id)))) {
            return "redirect:/";
        }
        System.out.println(this.bookingService.findAllByUserId(id));

        model.addAttribute("reservationMap", this.bookingService.findAllByUserId(id));
        model.addAttribute("state", "manage");
        return "booking-manage";
    }


    @GetMapping(path = "/manage/{id}/edit")
    public String edit(Model model,
                       @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!(this.sessionData.isAdminOrEmployee() ||
                (this.sessionData.isLogged() && sessionData.getUser().getId().equals(id)))) {
            return "redirect:/";
        }
        Optional<User> userBox = this.userService.findById(id);
        if (userBox.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("userModel", userBox.get());
        model.addAttribute("state", "edit");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "register";
    }

    @PostMapping(path = "/manage/{id}/edit")
    public String edit(@PathVariable Long id,
                       @ModelAttribute User user) {
        if (!(this.sessionData.isAdminOrEmployee() ||
                (this.sessionData.isLogged() && sessionData.getUser().getId().equals(id)))) {
            return "redirect:/";
        }

        try {
            UserValidator.validateEditedUser(user);
            Optional<User> userBox = this.userService.findById(id);
            if (userBox.isEmpty()) {
                return "redirect:/users/manage";
            }
            user.setId(id);
            user.setLogin(userBox.get().getLogin());
            user.setPassword(userBox.get().getPassword());
            user.getAddress().setId(userBox.get().getAddress().getId());
            if (!this.sessionData.isAdmin()) {
                user.setRole(userBox.get().getRole());
            }
            this.userService.update(user);
        } catch (UserValidationException e) {
            this.sessionData.setFormError("Wprowadź poprawne dane do formularza");
            e.printStackTrace();
            return "redirect:/users/register";
        }
        this.sessionData.setFormInfo("Edycja zakończona sukcesem.");
        return "redirect:/users/manage/" + id + "/edit";
    }

    @GetMapping(path = "/manage/edit/{id}/change-pass")
    public String changePass(Model model,
                             @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!(this.sessionData.isAdmin() ||
                (this.sessionData.isLogged() && sessionData.getUser().getId().equals(id)))) {
            return "redirect:/";
        }
        Optional<User> userBox = this.userService.findById(id);
        if (userBox.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("changePassModel", new ChangePassDTO());

        model.addAttribute("state", "edit");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "change-pass";
    }

    @PostMapping(path = "/manage/edit/{id}/change-pass")
    public String changePass(Model model,
                             @PathVariable Long id,
                             @ModelAttribute ChangePassDTO changePassDTO) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!(this.sessionData.isAdmin() ||
                (this.sessionData.isLogged() && sessionData.getUser().getId().equals(id)))) {
            return "redirect:/";
        }
        Optional<User> userBox = this.userService.findById(id);
        if (userBox.isEmpty()) {
            return "redirect:/";
        }

        changePassDTO.setUser(userBox.get());

        try {
            this.userService.changePassword(changePassDTO);
        } catch (UserValidationException e) {
            this.sessionData.setFormError("Wprowadź poprawne dane do formularza");
            return "redirect:/users/manage/" + id + "/edit/change-pass";
        }

        this.sessionData.setFormInfo("Hasło zostało zmienione.");
        return "redirect:/users/manage/" + id + "/edit";
    }

    @GetMapping(path = "/manage/{id}/delete")
    public String deleteUser(Model model,
                             @PathVariable long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        try {
            String login = userService.findById(id).get().getLogin();
            this.userService.delete(id);
            this.sessionData.setFormInfo("Usunięto użytkownika " + login);
        } catch (MethodArgumentTypeMismatchException | IllegalArgumentException | NoSuchElementException e) {
            System.out.println("User not found or try to delete admin account.");
            this.sessionData.setFormError("Nie można usunąć tego użytkownika");
        }
        model.addAttribute("state", "edit");
        return "redirect:/users";
    }


}
