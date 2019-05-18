package ngocngan.store.controller;

import ngocngan.store.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ngan nnh on 5/16/2019
 * @project sweet
 */
@Controller
public class BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = { "admin" }, method = RequestMethod.GET) public ModelAndView index() {
        return setViewName("admin/index");
    }

    @RequestMapping(value = { "/404" }, method = RequestMethod.GET) public ModelAndView _404() {
        return setViewName("404");
    }

    static ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    public static String getMethodName() {
        return new Throwable().getStackTrace()[2].getMethodName();
    }

    static String getUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    static ModelAndView setViewName(String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName(viewName);
            LOGGER.info("JUST ACCESS TO: " + viewName);
            return modelAndView;
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/404");
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return modelAndView;
        }
    }

    static List<?> getList(List<?> list) {
        try {
            LOGGER.info(Constant.LOGGER_INFO_LIST_SIZE(list.size()));
            return list;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return new ArrayList<>();
        }
    }
}
