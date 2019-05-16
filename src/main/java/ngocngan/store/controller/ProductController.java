package ngocngan.store.controller;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Product;
import ngocngan.store.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ngan nnh on 5/13/2019
 * @project sweet
 */
@Controller
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private static ProductRepository productRepository;

    @Autowired public ProductController(ProductRepository productRepository) {
        ProductController.productRepository = productRepository;
    }

    private List<Product> getAllProduct() {
        try {
            List<Product> products = productRepository.findAll();
            LOGGER.info(Constant.LOGGER_INFO_LIST_SIZE(products.size()));
            return products;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_WARN(e));
            return new ArrayList<>();
        }
    }

    private Product getProductByUUID(String uuid) {
        try {
            Product product = new Product();
            if (productRepository.findByUuid(uuid) != null) {
                product = productRepository.findByUuid(uuid);
                LOGGER.info("THE PRODUCT " + product.getUuid() + " BE FOUND");
            } else {
                LOGGER.warn("THE PRODUCT " + uuid + " CAN'T BE FOUND");
            }
            return product;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_WARN(e));
            return new Product();
        }
    }

    private Product saveProduct(String uuid, String name, float priceIn, float priceOut, String details,
            String imagePath) {
        try {
            Product product = getProductByUUID(uuid);
            product.setUuid(uuid);
            product.setName(name);
            product.setPriceIn(priceIn);
            product.setPriceOut(priceOut);
            product.setDetails(details);
            product.setImagePath(imagePath);
            productRepository.save(product);
            LOGGER.info(Constant.LOGGER_INFO_SAVE_SUCCESS(name));
            return product;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_WARN(e));
            return new Product();
        }
    }

    private void deleteProduct(String uuid){
        try{
            productRepository.delete(getProductByUUID(uuid));
            LOGGER.info(Constant.LOGGER_INFO_DELETE_SUCCESS(uuid));
        }catch (Exception e){
            LOGGER.warn(Constant.LOGGER_WARN(e));
        }
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView,String uuid){
        Product product = getProductByUUID(uuid);
        if (product.getUuid() != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.setViewName("redirect:/404");
        }
        return modelAndView;
    }

    @RequestMapping(value = { "/admin/products" }, method = RequestMethod.GET) public ModelAndView products() {
        ModelAndView modelAndView = MainController.setViewName("admin/product/products");
        modelAndView.addObject("products", getAllProduct());
        return modelAndView;
    }

    @RequestMapping(value = { "/admin/product-add" }, method = RequestMethod.GET) public ModelAndView addProduct() {
        return MainController.setViewName("admin/product/product-add");
    }

    @RequestMapping(value = { "/admin/product-details/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView productDetails(@PathVariable String uuid) {
        ModelAndView modelAndView = MainController.setViewName("admin/product/product-details");
        return getModelAndView(modelAndView,uuid);
    }

    @RequestMapping(value = { "/admin/product-update/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView productUpdate(@PathVariable String uuid) {
        ModelAndView modelAndView = MainController.setViewName("admin/product/product-update");
        return getModelAndView(modelAndView,uuid);
    }

    @RequestMapping(value = { "/admin/delete/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String uuid) {
        deleteProduct(uuid);
        return MainController.setViewName("redirect:/admin/products/");
    }

    @RequestMapping(value = { "/save" }, method = RequestMethod.POST)
    public ModelAndView save(@RequestParam String uuid, @RequestParam String name, @RequestParam Float priceIn,
            @RequestParam Float priceOut, @RequestParam String details, @RequestParam String imagePath) {
        if (uuid.isEmpty()) {
            uuid = MainController.getUUID();
        }
        return MainController.setViewName(
                "redirect:/admin/product-details/" + saveProduct(uuid, name, priceIn, priceOut, details, imagePath)
                        .getUuid());
    }
}
