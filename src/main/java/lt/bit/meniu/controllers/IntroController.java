package lt.bit.meniu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping()
public class IntroController {
    @PermitAll
    @GetMapping("/")
    String intro(){
        return "intro";
    }
}
