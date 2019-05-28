package ngocngan.store.service;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * @author ngan nnh on 5/27/2019
 * @project store
 */
@Service
public class BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);
    private static UserService userService;

    @Autowired public BaseService(UserService userService) {
        BaseService.userService = userService;
    }

    private ModelAndView getModelAndViewAdmin() {
        return new ModelAndView();
    }

    public String getRandomUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public ModelAndView setViewName(String viewName) {
        ModelAndView modelAndView = getModelAndViewAdmin();
//        modelAndView.addObject("currentUser", userService.getUserByUUID("584C21D7-568F-4EC0-BD10-DE762849440F"));
//        Users user = (Users) modelAndView.getModelMap().get("currentUser");
//        if (viewName.equals("admin/login")) {
//            modelAndView.setViewName(viewName);
//        } else if (user != null) {
//            modelAndView.setViewName(viewName);
//            LOGGER.info("JUST ACCESS TO: " + viewName);
//        } else {
//            modelAndView.setViewName("redirect:/login");
//            LOGGER.info("HAS TO LOGIN FIRST!");
//        }
        modelAndView.setViewName(viewName);
        LOGGER.info("JUST ACCESS TO: " + viewName);
        return modelAndView;
    }
}
