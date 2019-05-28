package ngocngan.store.controller;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Product;
import ngocngan.store.repository.ProductRepository;
import ngocngan.store.service.BaseService;
import ngocngan.store.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ngan nnh on 5/16/2019
 * @project sweet
 */
@Controller
@RequestMapping(value = "/admin")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private static BaseService baseService;
    private static ProductService productService;

    @Autowired public ProductController(ProductService productService, BaseService baseService) {
        ProductController.baseService = baseService;
        ProductController.productService = productService;
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String uuid) {
        Product product = productService.getProductByUUID(uuid);
        if (product.getUuid() != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.setViewName("redirect:/404");
        }
        return modelAndView;
    }

    @RequestMapping(value = { "/products" }, method = RequestMethod.GET) public ModelAndView products() {
        ModelAndView modelAndView = baseService.setViewName("admin/product/products");
        modelAndView.addObject("products", productService.getProducts());
        return modelAndView;
    }

    @RequestMapping(value = { "/product-add" }, method = RequestMethod.GET) public ModelAndView addProduct() {
        return baseService.setViewName("admin/product/product-add");
    }

    @RequestMapping(value = { "/product-details/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView productDetails(@PathVariable String uuid) {
        ModelAndView modelAndView = baseService.setViewName("admin/product/product-details");
        return getModelAndView(modelAndView, uuid);
    }

    @RequestMapping(value = { "/product-update/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView productUpdate(@PathVariable String uuid) {
        ModelAndView modelAndView = baseService.setViewName("admin/product/product-update");
        return getModelAndView(modelAndView, uuid);
    }

    @RequestMapping(value = { "/product-delete/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String uuid) {
        productService.deleteProduct(uuid);
        return baseService.setViewName("redirect:/admin/products");
    }

    @RequestMapping(value = { "/product-save" }, method = RequestMethod.POST)
    public ModelAndView save(@RequestParam String uuid, @RequestParam String name, @RequestParam Float priceIn,
            @RequestParam Float priceOut, @RequestParam String details, @RequestParam String imagePath) {
        if (uuid.isEmpty()) {
            uuid = baseService.getRandomUUID();
        }
        return baseService.setViewName(
                "redirect:/admin/product-details/" + productService.saveProduct(uuid, name, priceIn, priceOut, details, imagePath)
                        .getUuid());
    }
}
