package com.kuba.shooting.range.management.controllers;

import com.kuba.shooting.range.management.controllers.utils.ModelUtils;
import com.kuba.shooting.range.management.services.AmmoService;
import com.kuba.shooting.range.management.services.GunService;
import com.kuba.shooting.range.management.session.SessionData;
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
    public String arsenal(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        model.addAttribute("ammoList", this.ammoService.findAll());
        model.addAttribute("gunList", this.gunService.findAll());
        return "arsenal";
    }


}
