package lt.bit.meniu.controllers;


import lt.bit.meniu.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc/auth")
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/login")
    String login() {
        return "login";
    }

}
