package ngocngan.store.controller;

import ngocngan.store.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author ngan nnh on 5/16/2019
 * @project sweet
 */
@Controller
public class BaseController {

    private static BaseService baseService;

    @Autowired public BaseController(BaseService baseService) {
        BaseController.baseService = baseService;
    }

    @RequestMapping(value = { "/" }, method = RequestMethod.GET) public ModelAndView homepage() {
        return baseService.setViewName("customer/index");
    }

    @RequestMapping(value = { "/categories" }, method = RequestMethod.GET) public ModelAndView categories() {
        return baseService.setViewName("customer/categories");
    }

    @RequestMapping(value = { "admin" }, method = RequestMethod.GET) public ModelAndView index() {
        return baseService.setViewName("admin/index");
    }

    @RequestMapping(value = { "/404" }, method = RequestMethod.GET) public ModelAndView _404() {
        return baseService.setViewName("404");
    }

    @RequestMapping(value = { "/access-denied" }, method = RequestMethod.GET) public ModelAndView accessDenied() {
        return baseService.setViewName("access-denied");
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET) public ModelAndView login() {
        return baseService.setViewName("admin/login");
    }
}
