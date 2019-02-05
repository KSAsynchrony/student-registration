package com.student.registration.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    ConfigVariables configVariables;

    public MainController(ConfigVariables configVariables) {
        this.configVariables = configVariables;
    }

    @GetMapping("/")
    public String getHomePage(ModelMap modelMap) {
        modelMap.put("displayVariable", configVariables.getDisplayVaribale());
        return "home";
    }
}