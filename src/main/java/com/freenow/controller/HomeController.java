package com.freenow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@ApiIgnore
public class HomeController
{

    @RequestMapping("/")
    public String home()
    {
        return "redirect:/v1/swagger-ui/";
    }

}
