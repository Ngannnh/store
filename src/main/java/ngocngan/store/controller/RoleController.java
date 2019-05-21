package ngocngan.store.controller;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Role;
import ngocngan.store.repository.RoleRepository;
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
 * @author ngan nnh on 5/17/2019
 * @project store
 */
@Controller
@RequestMapping(value = "/admin")
public class RoleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    private static RoleRepository roleRepository;

    @Autowired public RoleController(RoleRepository roleRepository) {
        RoleController.roleRepository = roleRepository;
    }

    private List<Role> getRoles(){
        try {
            List<Role> roles = roleRepository.findAll();
            LOGGER.info(Constant.LOGGER_INFO_ROLES_SIZE(roles.size()));
            return roles;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return null;
        }
    }

    private Role getRoleByUUID(String uuid) {
        try {
            Role role = new Role();
            if (roleRepository.findByUuid(uuid) != null) {
                role = roleRepository.findByUuid(uuid);
                LOGGER.info(Constant.LOGGER_ROLE_FOUND(role.getUuid()));
            } else {
                LOGGER.warn(Constant.LOGGER_ROLE_NOT_FOUND(uuid));
            }
            return role;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return new Role();
        }
    }

    private void saveRole(String uuid, String s) {
        try {
            Role role = getRoleByUUID(uuid);
            role.setUuid(uuid);
            role.setRole(s);
            roleRepository.save(role);
            LOGGER.info(Constant.LOGGER_SAVE_ROLE_SUCCESS(s));
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
        }
    }

    private void deleteRole(String uuid) {
        try {
            roleRepository.delete(getRoleByUUID(uuid));
            LOGGER.info(Constant.LOGGER_DELETE_ROLE_SUCCESS(uuid));
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
        }
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String uuid) {
        Role role = getRoleByUUID(uuid);
        if (role.getUuid() != null) {
            modelAndView.addObject("role", role);
        } else {
            modelAndView.setViewName("redirect:/404");
        }
        return modelAndView;
    }

    @RequestMapping(value = { "/roles" }, method = RequestMethod.GET) public ModelAndView roles() {
        ModelAndView modelAndView = BaseController.setViewName("admin/role/roles");
        modelAndView.addObject("roles", getRoles());
        return modelAndView;
    }

    @RequestMapping(value = { "/role-add" }, method = RequestMethod.GET) public ModelAndView addRole() {
        return BaseController.setViewName("admin/role/role-add");
    }

    @RequestMapping(value = { "/role-update/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView roleUpdate(@PathVariable String uuid) {
        ModelAndView modelAndView = BaseController.setViewName("admin/role/role-update");
        return getModelAndView(modelAndView, uuid);
    }

    @RequestMapping(value = { "/role-delete/{uuid}" }, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String uuid) {
        deleteRole(uuid);
        return BaseController.setViewName("redirect:/admin/roles");
    }

    @RequestMapping(value = { "/role-save" }, method = RequestMethod.POST)
    public ModelAndView save(@RequestParam String uuid, @RequestParam String role) {
        if (uuid.isEmpty()) {
            uuid = BaseController.getUUID();
        }
        saveRole(uuid, role);
        return BaseController.setViewName("redirect:/admin/roles");
    }
}
