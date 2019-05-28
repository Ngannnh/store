package ngocngan.store.controller;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Role;
import ngocngan.store.repository.RoleRepository;
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

/**
 * @author ngan nnh on 5/17/2019
 * @project store
 */
@Controller
@RequestMapping(value = "/admin")
public class RoleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    private static BaseService baseService;
    private static UserService userService;

    @Autowired public RoleController(UserService userService, BaseService baseService) {
        RoleController.baseService = baseService;
        RoleController.userService = userService;
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String uuid) {
        Role role = userService.getRoleByUUID(uuid);
        if (role.getUuid() != null) {
            modelAndView.addObject("role", role);
        } else {
            modelAndView.setViewName("redirect:/404");
        }
        return modelAndView;
    }

    @RequestMapping(value = { "/roles" }, method = RequestMethod.GET) public ModelAndView roles() {
        ModelAndView modelAndView = baseService.setViewName("admin/role/roles");
        modelAndView.addObject("roles", userService.getRoles());
        return modelAndView;
    }

    @RequestMapping(value = { "/role-add" }, method = RequestMethod.GET) public ModelAndView addRole() {
        return baseService.setViewName("admin/role/role-add");
    }

    @RequestMapping(value = { "/role-update/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView roleUpdate(@PathVariable String uuid) {
        ModelAndView modelAndView = baseService.setViewName("admin/role/role-update");
        return getModelAndView(modelAndView, uuid);
    }

    @RequestMapping(value = { "/role-delete/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String uuid) {
        userService.deleteRole(uuid);
        return baseService.setViewName("redirect:/admin/roles");
    }

    @RequestMapping(value = { "/role-save" }, method = RequestMethod.POST)
    public ModelAndView save(@RequestParam String uuid, @RequestParam String role) {
        if (uuid.isEmpty()) {
            uuid = baseService.getRandomUUID();
        }
        userService.saveRole(uuid, role);
        return baseService.setViewName("redirect:/admin/roles");
    }
}
