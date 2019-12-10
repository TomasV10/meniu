package lt.bit.meniu.controllers;

import lt.bit.meniu.dto.ProductDto;
import lt.bit.meniu.entities.Product;
import lt.bit.meniu.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        modelMap.put("title", "Meat");
        modelMap.put("time", LocalDateTime.now());
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

}
