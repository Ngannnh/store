//package ngocngan.store.controller;
//
//import ngocngan.store.constant.Constant;
//import ngocngan.store.models.Product;
//import ngocngan.store.models.User;
//import ngocngan.store.repository.userRepository;
//import ngocngan.store.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author ngan nnh on 5/16/2019
// * @project sweet
// */
//@Controller
//public class UserController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//    private static UserRepository userRepository;
//
//    @Autowired public UserController(UserRepository userRepository) {
//        UserController.userRepository = userRepository;
//    }
//
//    private List<User> getAllUser() {
//        try {
//            List<User> users = userRepository.findAll();
//            LOGGER.info(Constant.LOGGER_INFO_LIST_SIZE(users.size()));
//            return users;
//        } catch (Exception e) {
//            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
//            return new ArrayList<>();
//        }
//    }
//
//    private Product getProductByUUID(String uuid) {
//        try {
//            Product product = new Product();
//            if (userRepository.findByUuid(uuid) != null) {
//                product = userRepository.findByUuid(uuid);
//                LOGGER.info(Constant.LOGGER_PRODUCT_FOUND(product.getUuid()));
//            } else {
//                LOGGER.warn(Constant.LOGGER_PRODUCT_NOT_FOUND(uuid));
//            }
//            return product;
//        } catch (Exception e) {
//            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
//            return new Product();
//        }
//    }
//
//    private Product saveProduct(String uuid, String name, float priceIn, float priceOut, String details,
//            String imagePath) {
//        try {
//            Product product = getProductByUUID(uuid);
//            product.setUuid(uuid);
//            product.setName(name);
//            product.setPriceIn(priceIn);
//            product.setPriceOut(priceOut);
//            product.setDetails(details);
//            product.setImagePath(imagePath);
//            userRepository.save(product);
//            LOGGER.info(Constant.LOGGER_SAVE_PRODUCT_SUCCESS(name));
//            return product;
//        } catch (Exception e) {
//            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
//            return new Product();
//        }
//    }
//
//    private void deleteProduct(String uuid){
//        try{
//            userRepository.delete(getProductByUUID(uuid));
//            LOGGER.info(Constant.LOGGER_DELETE_PRODUCT_SUCCESS(uuid));
//        }catch (Exception e){
//            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
//        }
//    }
//
//    private ModelAndView getModelAndView(ModelAndView modelAndView,String uuid){
//        Product product = getProductByUUID(uuid);
//        if (product.getUuid() != null) {
//            modelAndView.addObject("product", product);
//        } else {
//            modelAndView.setViewName("redirect:/404");
//        }
//        return modelAndView;
//    }
//
//    @RequestMapping(value = { "/admin/products" }, method = RequestMethod.GET) public ModelAndView products() {
//        ModelAndView modelAndView = BaseController.setViewName("admin/product/products");
//        modelAndView.addObject("products", getAllUser());
//        return modelAndView;
//    }
//
//    @RequestMapping(value = { "/admin/product-add" }, method = RequestMethod.GET) public ModelAndView addProduct() {
//        return BaseController.setViewName("admin/product/product-add");
//    }
//
//    @RequestMapping(value = { "/admin/product-details/{uuid}" }, method = RequestMethod.GET)
//    public ModelAndView productDetails(@PathVariable String uuid) {
//        ModelAndView modelAndView = BaseController.setViewName("admin/product/product-details");
//        return getModelAndView(modelAndView,uuid);
//    }
//
//    @RequestMapping(value = { "/admin/product-update/{uuid}" }, method = RequestMethod.GET)
//    public ModelAndView productUpdate(@PathVariable String uuid) {
//        ModelAndView modelAndView = BaseController.setViewName("admin/product/product-update");
//        return getModelAndView(modelAndView,uuid);
//    }
//
//    @RequestMapping(value = { "/admin/product-delete/{uuid}" }, method = RequestMethod.GET)
//    public ModelAndView delete(@PathVariable String uuid) {
//        deleteProduct(uuid);
//        return BaseController.setViewName("redirect:/admin/products/");
//    }
//
//    @RequestMapping(value = { "/product/save" }, method = RequestMethod.POST)
//    public ModelAndView save(@RequestParam String uuid, @RequestParam String name, @RequestParam Float priceIn,
//            @RequestParam Float priceOut, @RequestParam String details, @RequestParam String imagePath) {
//        if (uuid.isEmpty()) {
//            uuid = BaseController.getUUID();
//        }
//        return BaseController.setViewName(
//                "redirect:/admin/product-details/" + saveProduct(uuid, name, priceIn, priceOut, details, imagePath)
//                        .getUuid());
//    }
//
//}
