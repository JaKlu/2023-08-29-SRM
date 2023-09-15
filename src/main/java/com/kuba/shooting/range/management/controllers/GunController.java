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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RequestMapping(path = "/guns")
@Controller
public class GunController {

    private SessionData sessionData;
    private GunService gunService;


    @GetMapping(path = "/manage")
    public String getAllGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        createGunDtoList(model);
        model.addAttribute("state", "info");
        return "guns";
    }

    @GetMapping(path = "/manage/release")
    public String releaseGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        createGunDtoList(model);
        model.addAttribute("state", "release");
        return "guns";
    }

    @PostMapping(path = "/manage/release")
    public String releaseGuns(Model model,
                              @ModelAttribute GunCreationDto gunForm) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        this.gunService.releaseGuns(gunForm);
        return "redirect:/guns/manage/release";
    }

    @GetMapping(path = "/manage/take")
    public String takeGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        createGunDtoList(model);
        model.addAttribute("state", "take");
        return "guns";
    }

    @PostMapping(path = "/manage/take")
    public String takeGuns(Model model,
                           @ModelAttribute GunCreationDto gunForm) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        this.gunService.takeGuns(gunForm);
        model.addAttribute("state", "take");
        return "redirect:/guns/manage/take";
    }

    @GetMapping(path = "/manage/edit")
    public String editGuns(Model model,
                           @RequestParam(required = false) String formInfo,
                           @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }
        createGunDtoList(model);
        model.addAttribute("state", "edit");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "guns";
    }

    @GetMapping(path = "/manage/edit/{id}")
    public String editGauge(Model model,
                            @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        Optional<Gun> gunBox = this.gunService.findById(id);
        if (gunBox.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("gunModel", gunBox.get());
        model.addAttribute("state", "edit");
        return "guns-edit";
    }

    @PostMapping(path = "/manage/edit/{id}")
    public String editGun(Model model,
                          @PathVariable Long id,
                          @ModelAttribute Gun gun) {
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }
        Optional<Gun> gunBox = this.gunService.findById(id);
        if (gunBox.isEmpty()) {
            return "redirect:/";
        }
        gun.setId(id);
        this.gunService.saveGun(gun);

        return "redirect:/guns/manage/edit";
    }

    @GetMapping(path = "/manage/add")
    public String addGun(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        model.addAttribute("gunModel", new Gun());
        model.addAttribute("state", "add");
        return "guns-edit";
    }

    @PostMapping(path = "/manage/add")
    public String addGun(Model model,
                           @ModelAttribute Gun gun) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        this.gunService.saveGun(gun);
        return "redirect:/guns/manage/edit";
    }

    @GetMapping(path = "/manage/delete/{id}")
    public String deleteGauge(Model model,
                              @PathVariable long id,
                              @RequestParam(required = false) String formInfo,
                              @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        try {
            this.gunService.deleteGun(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not delete. Gun not in stock.");
            this.sessionData.setFormError("Nie usunięto. Brak broni w magazynie.");
        }
        model.addAttribute("state", "edit");
        return "redirect:/guns/manage/edit";
    }


    private void createGunDtoList(Model model) {
        GunCreationDto gunForm = new GunCreationDto();
        for (Gun gun : this.gunService.findAll()) {
            gunForm.addDTO(new GunDTO(gun));
        }
        model.addAttribute("gunForm", gunForm);
    }
}
