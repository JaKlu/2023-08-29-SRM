package ovh.jakubk.shooting.range.management.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ovh.jakubk.shooting.range.management.controllers.utils.ModelUtils;
import ovh.jakubk.shooting.range.management.exceptions.AmmoOnStockException;
import ovh.jakubk.shooting.range.management.exceptions.ResourceNotFoundException;
import ovh.jakubk.shooting.range.management.model.Ammo;
import ovh.jakubk.shooting.range.management.model.dto.view.AmmoListViewDTO;
import ovh.jakubk.shooting.range.management.services.AmmoService;
import ovh.jakubk.shooting.range.management.session.SessionData;

import java.util.Optional;

@AllArgsConstructor
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

        //createAmmoDtoList(model);
        model.addAttribute("ammoForm", new AmmoListViewDTO(this.ammoService.findAllForManageView()));
        model.addAttribute("state", "info");
        return "ammo";
    }

    @GetMapping(path = "/manage/get")
    public String getAmmo(Model model,
                          @RequestParam(required = false) String formInfo,
                          @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

        //createAmmoDtoList(model);
        model.addAttribute("ammoForm", new AmmoListViewDTO(this.ammoService.findAllForManageView()));
        model.addAttribute("state", "get");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "ammo";
    }

    @PostMapping(path = "/manage/get")
    public String getAmmo(Model model,
//                          @ModelAttribute AmmoCreationDto ammoForm
                          @Valid @ModelAttribute AmmoListViewDTO ammoForm

    ) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

        try {
//            this.ammoService.getAmmo(ammoForm);
            this.ammoService.manageAmmoView(ammoForm, false);
        } catch (IllegalArgumentException | ResourceNotFoundException | AmmoOnStockException e) {
            System.out.println("Could not get.");
            this.sessionData.setFormError("Podaj poprawne dane");
        }
        return "redirect:/ammo/manage/get";
    }

    @GetMapping(path = "/manage/supply")
    public String supplyAmmo(Model model,
                             @RequestParam(required = false) String formInfo,
                             @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }

//        createAmmoDtoList(model);
        model.addAttribute("ammoForm", new AmmoListViewDTO(this.ammoService.findAllForManageView()));
        model.addAttribute("state", "supply");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "ammo";
    }

    @PostMapping(path = "/manage/supply")
    public String supplyAmmo(Model model,
                             @ModelAttribute AmmoListViewDTO ammoForm) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdminOrEmployee()) {
            return "redirect:/";
        }
        try {
//            this.ammoService.supplyAmmo(ammoForm);
            this.ammoService.manageAmmoView(ammoForm, true);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not supply.");
            this.sessionData.setFormError("Podaj poprawne dane");
        }
        return "redirect:/ammo/manage/supply";
    }

    @GetMapping(path = "/manage/edit")
    public String editGauges(Model model,
                             @RequestParam(required = false) String formInfo,
                             @RequestParam(required = false) String formError) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }
        //createAmmoDtoList(model);
        model.addAttribute("ammoForm", new AmmoListViewDTO(this.ammoService.findAllForManageView()));
        model.addAttribute("state", "edit");
        model.addAttribute("formInfo", this.sessionData.getFormInfo());
        model.addAttribute("formError", this.sessionData.getFormError());
        return "ammo";
    }

    @GetMapping(path = "/manage/edit/{id}")
    public String editGauge(Model model,
                            @PathVariable Long id) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        Optional<Ammo> ammoBox = this.ammoService.findById(id);
        if (ammoBox.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("ammoModel", ammoBox.get());
        model.addAttribute("state", "edit");
        return "ammo-edit";
    }

    @PostMapping(path = "/manage/edit/{id}")
    public String editGauge(Model model,
                            @PathVariable Long id,
                            @ModelAttribute Ammo ammo) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        Optional<Ammo> ammoBox = this.ammoService.findById(id);
        if (ammoBox.isEmpty()) {
            return "redirect:/";
        }
        ammo.setId(id);
        ammo.setQuantity(ammoBox.get().getQuantity());
        this.ammoService.saveGauge(ammo);

        return "redirect:/ammo/manage/edit";
    }


    @GetMapping(path = "/manage/add")
    public String addGauge(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        model.addAttribute("ammoModel", new Ammo());
        model.addAttribute("state", "add");
        return "ammo-edit";
    }

    @PostMapping(path = "/manage/add")
    public String addGauge(Model model,
                           @ModelAttribute Ammo ammo) {
        ModelUtils.addCommonDataToModel(model, sessionData);
        if (!this.sessionData.isAdmin()) {
            return "redirect:/";
        }

        this.ammoService.saveGauge(ammo);
        return "redirect:/ammo/manage/edit";
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
            this.ammoService.deleteGauge(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not delete. Stock is higher than 0.");
            this.sessionData.setFormError("Nie usunięto. Stan magazynowy musi wynosić zero.");
        }
        model.addAttribute("state", "edit");
        return "redirect:/ammo/manage/edit";
    }


/*    private void createAmmoDtoList(Model model) {
        AmmoCreationDto ammoForm = new AmmoCreationDto();
        for (Ammo ammo : this.ammoService.findAll()) {
            ammoForm.getDtoList().add(new AmmoDTO(ammo));
            //ammoForm.addDTO(new AmmoDTO(ammo));
        }
        model.addAttribute("ammoForm", ammoForm);
    }*/
}
