package com.kuba.shooting.range.management.controllers;

import com.kuba.shooting.range.management.controllers.utils.ModelUtils;
import com.kuba.shooting.range.management.exceptions.LoginAlreadyExistException;
import com.kuba.shooting.range.management.exceptions.UserValidationException;
import com.kuba.shooting.range.management.model.User;
import com.kuba.shooting.range.management.services.UserService;
import com.kuba.shooting.range.management.session.SessionData;
import com.kuba.shooting.range.management.validators.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class UserController {

    private SessionData sessionData;
    private final UserService userService;

    @GetMapping(path = "/register")
    public String register(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("userModel", new User());
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "register";
    }

    @PostMapping(path = "/register")
    public String register(@ModelAttribute User user,
                           @RequestParam String password2) {
        try {
            UserValidator.validateUser(user);
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
}
