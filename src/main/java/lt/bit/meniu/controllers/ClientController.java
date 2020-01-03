package lt.bit.meniu.controllers;

import lt.bit.meniu.dto.ClientDto;
import lt.bit.meniu.dto.ProductDto;
import lt.bit.meniu.entities.Client;
import lt.bit.meniu.entities.Product;
import lt.bit.meniu.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mvc/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/duomenys")
        public String getClient(){
            return "Busimi duomenys";
        }

    @GetMapping("/list")
    ModelAndView getClients(
            @RequestParam(defaultValue = "0", required = false) int size,
            @RequestParam(defaultValue = "0", required = false) int page,
            HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (page <= 0 && cookie.getName().equals("page")) {
                    page = Integer.parseInt(cookie.getValue());
                }
                if (size <= 0 && cookie.getName().equals("size")) {
                    size = Integer.parseInt(cookie.getValue());
                }
            }
        }
        if (size < 1) size = 10;
        if (page < 1) page = 1;

        response.addCookie(new Cookie("size", String.valueOf(size)));
        response.addCookie(new Cookie("page", String.valueOf(page)));

        Page<Client> clientPage = clientRepository.findAll(PageRequest.of(page - 1, size));

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("title", "Clients");
        modelMap.put("time", LocalDateTime.now());
        modelMap.put("clientPage", clientPage);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clients");
        modelAndView.addAllObjects(modelMap);

        return modelAndView;
    }


    @GetMapping("/{id}")
    public ModelAndView getClientById(@PathVariable Integer id) {
        ClientDto client = clientRepository.getOne(id).clientDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clientDescription");
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @RequestMapping("/newClient")
    public String showNewProductPage(Model model) { //kliento sarasas ir tipu
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        return "newClient";
    }



    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    RedirectView newPost(ClientDto clientDto) {
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setDateVisited(clientDto.getDateVisited());
        client.setProducts(clientDto.getProducts());
        clientRepository.save(client);
        return new RedirectView ( "/mvc/client/list");
    }

    @GetMapping("/delete")
    RedirectView deleteById(
            @RequestParam int id,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "1", required = false) int page,
            RedirectAttributes attributes) {
        clientRepository.deleteById(id);
        attributes.addAttribute("size", size);
        attributes.addAttribute("page", page);
        return new RedirectView("/mvc/client/list");

    }
}
