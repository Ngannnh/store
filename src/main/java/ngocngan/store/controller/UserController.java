package ngocngan.store.controller;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Role;
import ngocngan.store.models.Users;
import ngocngan.store.repository.UserRepository;
import ngocngan.store.service.BaseService;
import ngocngan.store.service.UserService;
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
@RequestMapping(value = { "/admin", "/user" })
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static RoleController roleController;
    private static BaseService baseService;
    private static UserService userService;

    @Autowired
    public UserController(RoleController roleController, BaseService baseService,
            UserService userService) {
        UserController.roleController = roleController;
        UserController.baseService = baseService;
        UserController.userService = userService;
    }

    @RequestMapping(value = { "/users" }, method = RequestMethod.GET) public ModelAndView users() {
        ModelAndView modelAndView = baseService.setViewName("admin/user/users");
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }

    @RequestMapping(value = { "/user-add" }, method = RequestMethod.GET) public ModelAndView addUser() {
        ModelAndView modelAndView = baseService.setViewName("admin/user/user-add");
        modelAndView.addObject("roles", userService.getRoles());
        return modelAndView;
    }

    @RequestMapping(value = { "/user-details/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView userDetails(@PathVariable String uuid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user/user-details");
        return getModelAndView(modelAndView, uuid);
    }

    @RequestMapping(value = { "/user-update/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView userUpdate(@PathVariable String uuid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user/user-update");
        modelAndView.addObject("roles", userService.getRoles());
        return getModelAndView(modelAndView, uuid);
    }

    @RequestMapping(value = { "/user-delete/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String uuid) {
        userService.deleteUser(uuid);
        return baseService.setViewName("redirect:/admin/users");
    }

    @RequestMapping(value = { "/user-save" }, method = RequestMethod.POST)
    public ModelAndView save(@RequestParam String uuid, @RequestParam String email, @RequestParam String password,
            @RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone,
            @RequestParam String city, @RequestParam String address, @RequestParam String image,
            @RequestParam String role) {
        if (uuid.isEmpty()) {
            uuid = baseService.getRandomUUID();
        }
        return baseService.setViewName(
                "redirect:/admin/user-details/" + userService.saveUser(uuid, email, password, firstName, lastName, phone, city,
                        address, image, role).getUuid());
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String uuid) {
        Users user = userService.getUserByUUID(uuid);
        if (user.getUuid() != null) {
            modelAndView.addObject("user", user);
        } else {
            modelAndView.setViewName("redirect:/404");
        }
        return modelAndView;
    }
}
