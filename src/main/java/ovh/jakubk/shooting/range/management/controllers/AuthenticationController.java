package ovh.jakubk.shooting.range.management.controllers;

import ovh.jakubk.shooting.range.management.controllers.utils.ModelUtils;
import ovh.jakubk.shooting.range.management.services.AuthenticationService;
import ovh.jakubk.shooting.range.management.session.SessionData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class AuthenticationController {

    private SessionData sessionData;
    private AuthenticationService authenticationService;

    @GetMapping(path = "/login")
    public String login(Model model,
                        @RequestParam(required = false) String formInfo,
                        @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "login";
    }

    @PostMapping(path = "/login")
    public String login(@RequestParam String login,
                        @RequestParam String password) {
        this.authenticationService.authenticate(login, password);
        if (this.sessionData.isLogged()) {
            return "redirect:/";
        }
        this.sessionData.setFormError("Wprowadź poprawne dane do formularza");
        return "redirect:/login";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        this.authenticationService.logout(request);
        return "redirect:/";
    }
}
