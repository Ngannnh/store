package ngocngan.store.service;

import ngocngan.store.models.Users;
import ngocngan.store.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ngan nnh on 5/28/2019
 * @project store
 */
@Service
public class LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private static UserRepository userRepository;

    @Autowired public LoginService(UserRepository userRepository) {
        LoginService.userRepository = userRepository;
    }

    private Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Users getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public ModelAndView checkLogin(String emailOrPhone, String password) {
        ModelAndView modelAndView = new ModelAndView();
        Users userEmail = getUserByEmail(emailOrPhone);
        Users userPhone = getUserByPhone(emailOrPhone);
        if (userEmail != null) {
            return checkPassword(userEmail, password, modelAndView);
        } else if (userPhone != null) {
            return checkPassword(userPhone, password, modelAndView);
        } else {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
    }

    private ModelAndView checkPassword(Users user, String password, ModelAndView modelAndView) {
        if (password.equals(user.getPassword())) {
            modelAndView.addObject("currentUser", user);
            modelAndView.setViewName("redirect:/admin");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
    }
}
