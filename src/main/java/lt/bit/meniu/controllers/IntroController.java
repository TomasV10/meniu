package lt.bit.meniu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class IntroController {
    @GetMapping("/intro")
    String intro(){
        return "intro";
    }
}
