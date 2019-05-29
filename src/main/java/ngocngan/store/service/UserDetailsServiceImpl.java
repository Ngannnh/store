package ngocngan.store.service;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Users;
import ngocngan.store.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ngan nnh on 5/27/2019
 * @project store
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private static final String ADMIN_EMAIL = "ngannnh@outlook.com";
    private static final String ADMIN_PASSWORD = "$2a$10$f5yoFm9cnOjaE/VrDyotu.clH4Pses.S53Bkqm9qJ1CkfMjo4FNVy";
    private static final String ADMIN_ROLE = "ADMIN";

    private UserRepository userRepository;

    @Autowired public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Users user;
            if (email.equals(ADMIN_EMAIL)) {
                user = initAdmin();
            } else {
                user = userRepository.findByEmail(email);
            }

            if (user == null) {
                LOGGER.warn("User not found: " + email);
                throw new UsernameNotFoundException("Email: " + email + "was not found in the database.");
            }

            LOGGER.info("Found user: " + email);

            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add(new SimpleGrantedAuthority(user.getRole()));

            return new User(user.getEmail(), user.getPassword(), grantedAuthorityList);
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return null;
        }
    }

    private Users initAdmin() {
        Users user = new Users();
        user.setUuid(UUID.randomUUID().toString().toUpperCase());
        user.setEmail(ADMIN_EMAIL);
        user.setPassword(ADMIN_PASSWORD);
        user.setRole(ADMIN_ROLE);
        return user;
    }
}
