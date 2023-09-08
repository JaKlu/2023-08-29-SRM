package com.kuba.shooting.range.management.controllers;

import com.kuba.shooting.range.management.controllers.utils.ModelUtils;
import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.dto.GunCreationDto;
import com.kuba.shooting.range.management.model.dto.GunDTO;
import com.kuba.shooting.range.management.services.GunService;
import com.kuba.shooting.range.management.session.SessionData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping(path = "/guns")
@Controller
public class GunController {

    private SessionData sessionData;
    private GunService gunService;

    @GetMapping(path = "")
    public String showAllGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        model.addAttribute("gunList", this.gunService.findAll());
        return "guns";
    }

    @GetMapping(path = "/manage")
    public String getAllGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        createGunDtoList(model);
        model.addAttribute("state", "info");
        return "guns-manage";
    }

    @GetMapping(path = "/manage/release")
    public String releaseGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        createGunDtoList(model);
        model.addAttribute("state", "release");
        return "guns-manage";
    }

    @PostMapping(path = "/manage/release")
    public String releaseGuns(Model model,
                              @ModelAttribute GunCreationDto gunForm) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        this.gunService.releaseGuns(gunForm);
        return "redirect:/guns/manage/release";
    }

    @GetMapping(path = "/manage/take")
    public String takeGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        createGunDtoList(model);
        model.addAttribute("state", "take");
        return "guns-manage";
    }

    @PostMapping(path = "/manage/take")
    public String takeGuns(Model model,
                           @ModelAttribute GunCreationDto gunForm) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        this.gunService.takeGuns(gunForm);
        model.addAttribute("state", "take");
        return "redirect:/guns/manage/take";
    }

    private void createGunDtoList(Model model) {
        GunCreationDto gunForm = new GunCreationDto();
        for (Gun gun : this.gunService.findAll()) {
            gunForm.addDTO(new GunDTO(gun));
        }
        model.addAttribute("gunForm", gunForm);
    }
}
