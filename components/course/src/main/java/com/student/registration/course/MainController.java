package com.student.registration.course;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Value("${student.api.url}")
    String studentApi;

    @GetMapping("/")
    public String getHomePage(ModelMap modelMap) {
        modelMap.put("studentApi", studentApi);
        return "home";
    }

}