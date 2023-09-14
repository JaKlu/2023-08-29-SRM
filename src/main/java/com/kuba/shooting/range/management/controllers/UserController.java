package com.kuba.shooting.range.management.controllers;

import com.kuba.shooting.range.management.controllers.utils.ModelUtils;
import com.kuba.shooting.range.management.exceptions.LoginAlreadyExistException;
import com.kuba.shooting.range.management.exceptions.UserValidationException;
import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.User;
import com.kuba.shooting.range.management.services.UserService;
import com.kuba.shooting.range.management.session.SessionData;
import com.kuba.shooting.range.management.validators.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/users")
public class UserController {

    private SessionData sessionData;
    private final UserService userService;

    @GetMapping(path = "")
    public String users(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
/*        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }*/
        model.addAttribute("userList", this.userService.findAll());

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
            return "redirect:/register";
        } catch (UserValidationException e) {
            this.sessionData.setFormError("Wprowadź poprawne dane do formularza");
            e.printStackTrace();
            return "redirect:/register";
        }
        this.sessionData.setFormInfo("Rejestracja zakończona sukcesem. Zaloguj się na swoje konto.");
        return "redirect:/login";
    }

    @GetMapping(path = "/manage/edit/{id}")
    public String edit(Model model,
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

        model.addAttribute("userModel", userBox.get());
        model.addAttribute("state", "edit");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "register";
    }

    @PostMapping(path = "/manage/edit/{id}")
    public String edit(@PathVariable Long id,
                       @ModelAttribute User user) {
        if (!(this.sessionData.isAdmin() ||
                (this.sessionData.isLogged() && sessionData.getUser().getId().equals(id)))) {
            return "redirect:/";
        }

        try {
            UserValidator.validateEditedUser(user);
            Optional<User> userBox = this.userService.findById(id);
            if (userBox.isEmpty()) {
                return "redirect:/users";
            }
            user.setId(id);
            user.setLogin(userBox.get().getLogin());
            user.setPassword(userBox.get().getPassword());
            if (!this.sessionData.isAdmin()) {
                user.setRole(User.Role.USER);
            }
            this.userService.update(user);
        } catch (UserValidationException e) {
            this.sessionData.setFormError("Wprowadź poprawne dane do formularza");
            e.printStackTrace();
            return "redirect:/users/register";
        }
        this.sessionData.setFormInfo("Edycja zakończona sukcesem.");
        return "redirect:/users/manage/edit/" + id;
    }
}
