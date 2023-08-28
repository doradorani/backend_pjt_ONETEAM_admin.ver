package com.oneteam.dormeaseadmin;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class HomeController {

    @RequestMapping(value = {"", "/"})
    public String home() {
        log.info("home()");

        return "home";
    }
}
