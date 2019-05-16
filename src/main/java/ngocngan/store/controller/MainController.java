package ngocngan.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.UUID;

/**
 * @author ngan nnh on 5/13/2019
 * @project sweet
 */
@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = { "admin" }, method = RequestMethod.GET) public ModelAndView index() {
        return setViewName("admin/index");
    }

    @RequestMapping(value = { "/404" }, method = RequestMethod.GET) public ModelAndView _404() {
        return setViewName("404");
    }

    static ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    public static String getMethodName(){
        return new Throwable().getStackTrace()[2].getMethodName();
    }

    static String getUUID(){
        return UUID.randomUUID().toString().toUpperCase();
    }

    static ModelAndView setViewName(String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
