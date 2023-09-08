package com.kuba.shooting.range.management.controllers;

import com.kuba.shooting.range.management.controllers.utils.ModelUtils;
import com.kuba.shooting.range.management.session.SessionData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping(path = "/booking")
@Controller
public class BookingController {

    private SessionData sessionData;

    @GetMapping(path = "")
    public String booking(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        return "booking";
    }

    @GetMapping(path = "/manage")
    public String bookingManage(Model model) {
        ModelUtils.addCommonDataToModel(model, sessionData);

        return "redirect:/booking";
    }
}
