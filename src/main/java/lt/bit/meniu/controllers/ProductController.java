package lt.bit.meniu.controllers;

import lt.bit.meniu.dto.ClientDto;
import lt.bit.meniu.dto.ProductDto;
import lt.bit.meniu.entities.Client;
import lt.bit.meniu.entities.Product;
import lt.bit.meniu.repositories.ClientRepository;
import lt.bit.meniu.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
@RequestMapping("/mvc/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;


    @GetMapping()
    ModelAndView getProducts(
            @RequestParam(defaultValue = "", required = false) String type,
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

        Page<Product> productPage = getProductsPage(type, size, page);

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("title", type);
        modelMap.put("time", LocalDateTime.now().withNano(0));
        modelMap.put("productPage", productPage);
        modelMap.put("productType", type);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        modelAndView.addAllObjects(modelMap);

        return modelAndView;
    }

    private Page<Product> getProductsPage(@RequestParam(defaultValue = "", required = false) String type, @RequestParam(defaultValue = "0", required = false) int size, @RequestParam(defaultValue = "0", required = false) int page) {
        if (!StringUtils.isEmpty(type)) {
            return productRepository.findAllByType(Product.ProductType.valueOf(type), PageRequest.of(page - 1, size));
        } else {
            return productRepository.findAll(PageRequest.of(page - 1, size));
        }
    }
    @GetMapping("/{id}")
    public ModelAndView getProductById(@PathVariable Integer id) {
        ProductDto product = productRepository.getOne(id).toDto();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDescription");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/delete")
    RedirectView deleteById(
            @RequestParam int id,
            @RequestParam(defaultValue = "", required = false) String type,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "1", required = false) int page,
            RedirectAttributes attributes) {

        productRepository.deleteById(id);
        attributes.addAttribute("size", size);
        attributes.addAttribute("page", page);
        attributes.addAttribute("productType", type);
        return new RedirectView("/mvc/products?type={productType}");
    }

//    @GetMapping("/edit")
//    String editForm(@RequestParam int id, ModelMap modelMap) {
//        Product product = productRepository.getOne(id);
//        modelMap.addAttribute("product", product);
//        return "edit";
//    }

    @GetMapping("/edit")
    String editForm(@RequestParam int id, ModelMap modelMap) {
        ProductDto product = productRepository.getOne(id).toDto();
        modelMap.addAttribute("productDto", product); // client id = 1
        return "edit";
    }

    @PostMapping(value = "/edit",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    RedirectView saveEdit(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescriptions(productDto.getDescription());
        product.setPreparation(productDto.getPreparation());
        product.setType(Product.ProductType.valueOf(productDto.getType()));
        Client client = clientRepository.getOne(productDto.getClientId()); //client id = 0
        product.setClient(client);
        productRepository.save(product);
        return new RedirectView("/mvc/products");
    }

//    @PostMapping(value = "/edit",
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    RedirectView saveEdit(Product product, Client client, Product.ProductType type){
//        product.setClient(client);
//        product.setType(type);
//        productRepository.save(product);
//        return new RedirectView("/mvc/products");
//    }
    @RequestMapping("/newProduct")
    public String newProductPage(Model model) {
        ProductDto productDto = new ProductDto();
        productDto.setClientId(3);
        model.addAttribute("productDto", productDto);
        return "newProduct";
    }



    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    RedirectView newProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescriptions(productDto.getDescription());
        product.setPreparation(productDto.getPreparation());
        product.setType(Product.ProductType.valueOf(productDto.getType()));
        Client client = clientRepository.getOne(productDto.getClientId());
        product.setClient(client);
        productRepository.save(product);
        return new RedirectView ( "/mvc/products");
    }

}
