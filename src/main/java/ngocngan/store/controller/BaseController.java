package ngocngan.store.controller;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Users;
import ngocngan.store.repository.ProductRepository;
import ngocngan.store.repository.RoleRepository;
import ngocngan.store.service.BaseService;
import ngocngan.store.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * @author ngan nnh on 5/16/2019
 * @project sweet
 */
@Controller
public class BaseController {

    private static BaseService baseService;
    private static LoginService loginService;

    @Autowired public BaseController(BaseService baseService, LoginService loginService) {
        BaseController.baseService = baseService;
        BaseController.loginService = loginService;
    }

    @RequestMapping(value = { "admin" }, method = RequestMethod.GET) public ModelAndView index() {
        return baseService.setViewName("admin/index");
    }

    @RequestMapping(value = { "homepage" }, method = RequestMethod.GET) public ModelAndView homepage() {
        return baseService.setViewName("customer/index");
    }

    @RequestMapping(value = { "/404" }, method = RequestMethod.GET) public ModelAndView _404() {
        return baseService.setViewName("404");
    }

    @RequestMapping(value = { "/access-denied" }, method = RequestMethod.GET) public ModelAndView accessDenied() {
        return baseService.setViewName("access-denied");
    }

//    @RequestMapping(value = { "/register" }, method = RequestMethod.GET) public ModelAndView register() {
//        return baseService.setViewName("admin/register");
//    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET) public ModelAndView login() {
        return baseService.setViewName("admin/login");
    }

    @RequestMapping(value = { "/login-form" }, method = RequestMethod.POST)
    public ModelAndView login_form(@RequestParam String email, @RequestParam String password) {
        return loginService.checkLogin(email,password);
    }
}
