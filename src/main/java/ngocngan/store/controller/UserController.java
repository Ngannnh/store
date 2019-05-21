package ngocngan.store.controller;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Users;
import ngocngan.store.repository.UserRepository;
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
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static UserRepository userRepository;

    @Autowired public UserController(UserRepository userRepository) {
        UserController.userRepository = userRepository;
    }

    private List<Users> getAllUser() {
        try {
            List<Users> users = userRepository.findAll();
            LOGGER.info(Constant.LOGGER_INFO_USERS_SIZE(users.size()));
            return users;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return null;
        }
    }

    private Users getUserByUUID(String uuid) {
        try {
            Users user = new Users();
            if (userRepository.findByUuid(uuid) != null) {
                user = userRepository.findByUuid(uuid);
                LOGGER.info(Constant.LOGGER_USER_FOUND(user.getUuid()));
            } else {
                LOGGER.warn(Constant.LOGGER_USER_NOT_FOUND(uuid));
            }
            return user;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return new Users();
        }
    }

    private Users saveUser(String uuid, String email, String password, String firstName, String lastName, String phone,
            String city, String address, String image) {
        try {
            Users user = getUserByUUID(uuid);
            user.setUuid(uuid);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setCity(city);
            user.setAddress(address);
            user.setImage(image);
            userRepository.save(user);
            LOGGER.info(Constant.LOGGER_SAVE_USER_SUCCESS(email));
            return user;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return null;
        }
    }

    private void deleteUser(String uuid) {
        try {
            userRepository.delete(getUserByUUID(uuid));
            LOGGER.info(Constant.LOGGER_DELETE_USER_SUCCESS(uuid));
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
        }
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String uuid) {
        Users user = getUserByUUID(uuid);
        if (user.getUuid() != null) {
            modelAndView.addObject("user", user);
        } else {
            modelAndView.setViewName("redirect:/404");
        }
        return modelAndView;
    }

    @RequestMapping(value = { "/users" }, method = RequestMethod.GET) public ModelAndView users() {
        ModelAndView modelAndView = BaseController.setViewName("admin/user/users");
        modelAndView.addObject("users", getAllUser());
        return modelAndView;
    }

    @RequestMapping(value = { "/user-add" }, method = RequestMethod.GET) public ModelAndView addUser() {
        return BaseController.setViewName("admin/user/user-add");
    }

    @RequestMapping(value = { "/user-details/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView userDetails(@PathVariable String uuid) {
        ModelAndView modelAndView = BaseController.setViewName("admin/user/user-details");
        return getModelAndView(modelAndView, uuid);
    }

    @RequestMapping(value = { "/user-update/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView userUpdate(@PathVariable String uuid) {
        ModelAndView modelAndView = BaseController.setViewName("admin/user/user-update");
        return getModelAndView(modelAndView, uuid);
    }

    @RequestMapping(value = { "/user-delete/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String uuid) {
        deleteUser(uuid);
        return BaseController.setViewName("redirect:/admin/users/");
    }

    @RequestMapping(value = { "/user-save" }, method = RequestMethod.POST)
    public ModelAndView save(@RequestParam String uuid, @RequestParam String email, @RequestParam String password,
            @RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone,
            @RequestParam String city, @RequestParam String address, @RequestParam String image) {
        if (uuid.isEmpty()) {
            uuid = BaseController.getUUID();
        }
        return BaseController.setViewName(
                "redirect:/admin/user-details/" + saveUser(uuid, email, password, firstName, lastName, phone, city,
                        address,image).getUuid());
    }

}
