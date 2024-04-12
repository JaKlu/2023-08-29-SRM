package ovh.jakubk.shooting.range.management.controllers;

import ovh.jakubk.shooting.range.management.controllers.utils.ModelUtils;
import ovh.jakubk.shooting.range.management.services.AmmoService;
import ovh.jakubk.shooting.range.management.services.GunService;
import ovh.jakubk.shooting.range.management.session.SessionData;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class CommonController {
    private SessionData sessionData;
    private AmmoService ammoService;
    private GunService gunService;


    @GetMapping(path = "/")
    public String homePage(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        return "index";
    }

    @GetMapping(path = "/arsenal")
    public String showAllGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("gunList", this.gunService.findAllForArsenalView());
        return "arsenal";
    }
    @GetMapping(path = "/contact")
    public String contact(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        return "contact";
    }

    @GetMapping(path = "/stock")
    public String arsenal(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        model.addAttribute("ammoList", this.ammoService.findAllAmmo());
        model.addAttribute("gunList", this.gunService.findAllGuns());
        return "stock";
    }


}
