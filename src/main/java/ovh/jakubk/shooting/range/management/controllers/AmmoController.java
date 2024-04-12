package ovh.jakubk.shooting.range.management.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ovh.jakubk.shooting.range.management.controllers.utils.ModelUtils;
import ovh.jakubk.shooting.range.management.exceptions.AmmoOnStockException;
import ovh.jakubk.shooting.range.management.exceptions.ResourceNotFoundException;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoRequestDTO;
import ovh.jakubk.shooting.range.management.model.dto.rest.AmmoResponseDTO;
import ovh.jakubk.shooting.range.management.model.dto.view.AmmoListViewDTO;
import ovh.jakubk.shooting.range.management.services.AmmoService;
import ovh.jakubk.shooting.range.management.session.SessionData;


@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/ammo")
@Controller
public class AmmoController {
    private SessionData sessionData;
    private AmmoService ammoService;

    @GetMapping(path = {"", "/"})
    public String ammoHome() {
        return "redirect:/ammo/manage";
    }

    @GetMapping(path = "/manage")
    public String getAllAmmo(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

        model.addAttribute("ammoForm", new AmmoListViewDTO(this.ammoService.findAllForManageView()));
        model.addAttribute("state", "info");
        return "ammo";
    }

    @GetMapping(path = "/manage/get")
    public String getAmmo(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

        model.addAttribute("state", "get");
        model.addAttribute("ammoForm", new AmmoListViewDTO(this.ammoService.findAllForManageView()));
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "ammo";
    }

    @PostMapping(path = "/manage/get")
    public String getAmmo(Model model,
                          @Valid @ModelAttribute AmmoListViewDTO ammoForm,
                          BindingResult bindingResult) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

        try {
            if (bindingResult.hasErrors()) {
                log.warn("Wrong input");
                this.sessionData.setFormError("Podaj poprawne dane.");
                return "redirect:/ammo/manage/get";
            }
            this.ammoService.manageAmmoView(ammoForm, false);
        } catch (ResourceNotFoundException | AmmoOnStockException e) {
            log.warn(e.getMessage());
            this.sessionData.setFormError("Podaj poprawne dane.");
        }
        return "redirect:/ammo/manage/get";
    }

    @GetMapping(path = "/manage/supply")
    public String supplyAmmo(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

        model.addAttribute("state", "supply");
        model.addAttribute("ammoForm", new AmmoListViewDTO(this.ammoService.findAllForManageView()));
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "ammo";
    }

    @PostMapping(path = "/manage/supply")
    public String supplyAmmo(Model model,
                             @Valid @ModelAttribute AmmoListViewDTO ammoForm,
                             BindingResult bindingResult) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        try {
            if (bindingResult.hasErrors()) {
                log.warn("Wrong input");
                this.sessionData.setFormError("Podaj poprawne dane.");
                return "redirect:/ammo/manage/supply";
            }
            this.ammoService.manageAmmoView(ammoForm, true);
        } catch (ResourceNotFoundException e) {
            log.warn(e.getMessage());
            this.sessionData.setFormError("Podaj poprawne dane.");
        }
        return "redirect:/ammo/manage/supply";
    }

    @GetMapping(path = "/manage/edit")
    public String editAmmoView(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }
        model.addAttribute("state", "edit");
        model.addAttribute("ammoForm", new AmmoListViewDTO(this.ammoService.findAllForManageView()));
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "ammo";
    }

    @GetMapping(path = "/manage/edit/{id}")
    public String editAmmo(Model model,
                           @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        try {
            AmmoResponseDTO ammoResponseDTO = this.ammoService.findAmmoById(id);
            model.addAttribute("ammoModel", ammoResponseDTO);
        } catch (ResourceNotFoundException e) {
            log.warn(e.getMessage());
            return "redirect:/";
        }

        model.addAttribute("state", "edit");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "ammo-edit";
    }

    @PostMapping(path = "/manage/edit/{id}")
    public String editAmmo(Model model,
                           @PathVariable Long id,
                           @Valid @ModelAttribute AmmoRequestDTO ammoRequestDTO,
                           BindingResult bindingResult) {
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        try {
            if (bindingResult.hasErrors()) {
                log.warn("Wrong input");
                this.sessionData.setFormError("Podaj nazwę kalibru.");
                return "redirect:/ammo/manage/edit/{id}";
            }
            this.ammoService.updateAmmo(id, ammoRequestDTO);
            return "redirect:/ammo/manage/edit";
        } catch (ResourceNotFoundException e) {
            log.warn(e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping(path = "/manage/add")
    public String addAmmoGauge(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        model.addAttribute("ammoModel", new AmmoRequestDTO());
        model.addAttribute("state", "add");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "ammo-edit";
    }

    @PostMapping(path = "/manage/add")
    public String addGauge(Model model,
                           @Valid @ModelAttribute AmmoRequestDTO ammoRequestDTO,
                           BindingResult bindingResult) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        try {
            if (bindingResult.hasErrors()) {
                log.warn("Wrong input");
                this.sessionData.setFormError("Podaj nazwę kalibru.");
                return "redirect:/ammo/manage/add";
            }
            this.ammoService.saveAmmo(ammoRequestDTO);
        } catch (Exception e) {
            log.warn("Wrong data");
            this.sessionData.setFormError("Podaj poprawne dane.");
        }
        return "redirect:/ammo/manage/edit";
    }

    @GetMapping(path = "/manage/delete/{id}")
    public String deleteAmmo(Model model,
                             @PathVariable long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        try {
            this.ammoService.deleteAmmo(id);
        } catch (AmmoOnStockException e) {
            log.warn(e.getMessage());
            this.sessionData.setFormError("Nie usunięto. Stan magazynowy musi wynosić zero.");
        } catch (ResourceNotFoundException e) {
            log.warn(e.getMessage());
            this.sessionData.setFormError("Nie znaleziono amunicji do usunięcia.");
        }
        model.addAttribute("state", "edit");
        return "redirect:/ammo/manage/edit";
    }
}
