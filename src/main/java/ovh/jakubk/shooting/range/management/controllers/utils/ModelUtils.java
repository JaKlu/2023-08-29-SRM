package ovh.jakubk.shooting.range.management.controllers.utils;

import ovh.jakubk.shooting.range.management.session.SessionData;
import org.springframework.ui.Model;

public class ModelUtils {
    public static void addCommonDataToModel(Model model, SessionData sessionData) {
        model.addAttribute("user", sessionData.getUser());
        model.addAttribute("logged", sessionData.isLogged());
        model.addAttribute("admin", sessionData.isAdmin());
        model.addAttribute("employee", sessionData.isEmployee());
        model.addAttribute("adminOrEmployee", sessionData.isAdminOrEmployee());

    }
}
