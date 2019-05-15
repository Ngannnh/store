package ngocngan.store.controller;

import ngocngan.store.models.Product;
import ngocngan.store.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ngan nnh on 5/13/2019
 * @project sweet
 */
@Controller
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private static ProductRepository productRepository;

    @Autowired public ProductController(ProductRepository productRepository) {
        ProductController.productRepository = productRepository;
    }

    @RequestMapping(value = { "/products" }, method = RequestMethod.GET) public String products(Model model) {
        model.addAttribute("products",productRepository.findAll());
        return "product/products";
    }

    @RequestMapping(value = { "/product-add" }, method = RequestMethod.GET) public String addProduct(Model model) {
        return "product/product-add";
    }

    @RequestMapping(value = { "/product-details/{id}" }, method = RequestMethod.GET)
    public ModelAndView productDetails(@PathVariable Integer id) {
        ModelAndView modelAndView = MainController.getMAV();
        modelAndView.addObject("product", getProductById(id));
        modelAndView.setViewName("product/product-details");
        return modelAndView;
    }

    @RequestMapping(value = { "/product-update/{id}" }, method = RequestMethod.GET)
    public ModelAndView productUpdate(@PathVariable Integer id) {
        ModelAndView modelAndView = MainController.getMAV();
        modelAndView.addObject("product", getProductById(id));
        modelAndView.setViewName("product/product-update");
        return modelAndView;
    }

    @RequestMapping(value = { "/save" }, method = RequestMethod.POST)
    public String save(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer price,
            @RequestParam String details) {
        return "redirect:/product-details/" + addProduct(id, name, price, details).getId();
    }

    @RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer id) {
        ModelAndView modelAndView = MainController.getMAV();
        modelAndView.setViewName("redirect:/products/");
        productRepository.delete(getProductById(id));
        return modelAndView;
    }

    private Product getProductById(Integer id) {
        try {
            Product product = new Product();
            if (productRepository.findById(id).isPresent()) {
                product = productRepository.findById(id).get();
            }
            LOGGER.info("Product ID: " + product.getId());
            return product;
        } catch (Exception e) {
            LOGGER.error("Exception " + MainController.getMethodName() + ": " + e);
            return new Product();
        }
    }

    private Product addProduct(Integer id, String name, int price, String details) {
        try {
            Product product = getProductById(id);
            product.setName(name);
            product.setPrice(price);
            product.setDetails(details);
            productRepository.save(product);
            LOGGER.info("Save product " + name + " success!");
            return product;
        } catch (Exception e) {
            LOGGER.error("Exception " + MainController.getMethodName() + ": " + e);
            return new Product();
        }
    }
}
