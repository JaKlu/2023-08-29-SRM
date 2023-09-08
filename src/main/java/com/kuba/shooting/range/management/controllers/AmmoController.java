package com.kuba.shooting.range.management.controllers;

import com.kuba.shooting.range.management.controllers.utils.ModelUtils;
import com.kuba.shooting.range.management.model.Ammo;
import com.kuba.shooting.range.management.model.dto.AmmoCreationDto;
import com.kuba.shooting.range.management.model.dto.AmmoDTO;
import com.kuba.shooting.range.management.services.AmmoService;
import com.kuba.shooting.range.management.session.SessionData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping(path = "/ammo")
@Controller
public class AmmoController {
    private SessionData sessionData;
    private AmmoService ammoService;

    @GetMapping(path = "/manage")
    public String getAllAmmo(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
/*        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }*/
        createAmmoDtoList(model);
        model.addAttribute("state", "info");
        return "ammo";
    }

    @GetMapping(path = "/manage/get")
    public String getAmmo(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
/*        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }*/
        createAmmoDtoList(model);
        model.addAttribute("state", "get");
        return "ammo";
    }

    @PostMapping(path = "/manage/get")
    public String getAmmo(Model model,
                          @ModelAttribute AmmoCreationDto ammoForm) {
        ModelUtils.addCommonDataToModel(model, sessionData);
/*        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }*/
        this.ammoService.getAmmo(ammoForm);
        return "redirect:/ammo/manage/get";
    }

    @GetMapping(path = "/manage/supply")
    public String supplyAmmo(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
/*        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }*/
        createAmmoDtoList(model);
        model.addAttribute("state", "supply");
        return "ammo";
    }

    @PostMapping(path = "/manage/supply")
    public String supplyAmmo(Model model,
                             @ModelAttribute AmmoCreationDto ammoForm) {
        ModelUtils.addCommonDataToModel(model, sessionData);
/*        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }*/

        this.ammoService.supplyAmmo(ammoForm);
        return "redirect:/ammo/manage/supply";
    }

    @GetMapping(path = "/manage/add-gauge")
    public String addGauge(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        return "";
    }

    private void createAmmoDtoList(Model model) {
        AmmoCreationDto ammoForm = new AmmoCreationDto();
        for (Ammo ammo : this.ammoService.findAll()) {
            ammoForm.addDTO(new AmmoDTO(ammo));
        }
        model.addAttribute("ammoForm", ammoForm);
    }
}
