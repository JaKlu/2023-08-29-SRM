package ovh.jakubk.shooting.range.management.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ovh.jakubk.shooting.range.management.controllers.utils.ModelUtils;
import ovh.jakubk.shooting.range.management.exceptions.GunNotOnStockException;
import ovh.jakubk.shooting.range.management.exceptions.ResourceNotFoundException;
import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.rest.GunRequestDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.GunResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.GunListViewDTO;
import ovh.jakubk.shooting.range.management.services.AmmoService;
import ovh.jakubk.shooting.range.management.services.GunService;
import ovh.jakubk.shooting.range.management.session.SessionData;

import java.util.List;

@AllArgsConstructor
@RequestMapping(path = "/guns")
@Controller
@Slf4j
public class GunController {

    private SessionData sessionData;
    private GunService gunService;
    private AmmoService ammoService;

    @GetMapping(path = {"", "/"})
    public String gunsHome() {
        return "redirect:/guns/manage";
    }

    @GetMapping(path = "/manage")
    public String getAllGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

        model.addAttribute("gunForm", new GunListViewDTO(this.gunService.findAllForManageView()));
        model.addAttribute("state", "info");
        return "guns";
    }

    @GetMapping(path = "/manage/release")
    public String releaseGuns(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

        model.addAttribute("gunForm", new GunListViewDTO(this.gunService.findAllForManageView()));
        model.addAttribute("state", "release");
        return "guns";
    }

    @PostMapping(path = "/manage/release")
    public String releaseGuns(Model model,
                              @ModelAttribute GunListViewDTO gunForm) {
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

        model.addAttribute("gunForm", new GunListViewDTO(this.gunService.findAllForManageView()));
        model.addAttribute("state", "take");
        return "guns";
    }

    @PostMapping(path = "/manage/take")
    public String takeGuns(Model model,
                           @ModelAttribute GunListViewDTO gunForm) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        try {
            this.gunService.takeGuns(gunForm);
        } catch (ResourceNotFoundException | GunNotOnStockException e) {
            return "redirect:/guns/manage/take";
        }
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

        model.addAttribute("gunForm", new GunListViewDTO(this.gunService.findAllForManageView()));
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

        try {
            GunResponseDTO gunResponseDTO = this.gunService.findGunById(id);
            model.addAttribute("gunModel", gunResponseDTO);
        } catch (ResourceNotFoundException e) {
            log.warn("Requested gun does not exist");
            return "redirect:/";
        }

        List<String> gaugeList = this.ammoService.findAll().stream()
                .map(Ammo::getGauge)
                .toList();

        model.addAttribute("gaugeList", gaugeList);
        model.addAttribute("state", "edit");
        return "guns-edit";
    }

    @PostMapping(path = "/manage/edit/{id}")
    public String editGun(Model model,
                          @PathVariable Long id,
                          @ModelAttribute GunRequestDTO gunRequestDTO) {
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        try {
            this.gunService.updateGun(id, gunRequestDTO);
            return "redirect:/guns/manage/edit";
        } catch (ResourceNotFoundException e) {
            log.warn("Requested gun does not exist");
            return "redirect:/";
        }
    }

    @GetMapping(path = "/manage/add")
    public String addGun(Model model,
                         @RequestParam(required = false) String formInfo,
                         @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }
        List<String> gaugeList = this.ammoService.findAll().stream()
                .map(Ammo::getGauge)
                .toList();

        model.addAttribute("gunModel", new GunRequestDTO());
        model.addAttribute("gaugeList", gaugeList);
        model.addAttribute("state", "add");
        return "guns-edit";
    }

    @PostMapping(path = "/manage/add")
    public String addGun(Model model,
                         @ModelAttribute GunRequestDTO gunRequestDTO) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        try {
            this.gunService.saveGun(gunRequestDTO);
        } catch (NumberFormatException e) {
            log.warn("Wrong data");
            this.sessionData.setFormError("Podaj poprawne dane");
        }
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
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
        } catch (GunNotOnStockException e) {
            log.warn("Could not delete. Gun not in stock.");
            this.sessionData.setFormError("Nie usunięto. Brak broni w magazynie.");
        } catch (ResourceNotFoundException e) {
            log.warn("Requested gun does not exist");
            this.sessionData.setFormError("Nie znaleziono broni do usunięcia.");
        }
        model.addAttribute("state", "edit");
        return "redirect:/guns/manage/edit";
    }
}
