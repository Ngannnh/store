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
    private static final String ADMIN_PHONE = "0585851401";
    private static final String ADMIN_PASSWORD = "$2a$10$f5yoFm9cnOjaE/VrDyotu.clH4Pses.S53Bkqm9qJ1CkfMjo4FNVy";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String ADMIN_NAME = "NGAN";

    private UserRepository userRepository;

    @Autowired public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user;

        if (email.equals(ADMIN_EMAIL) || email.equals(ADMIN_PHONE)) {
            user = initAdmin();
            LOGGER.info("ADMIN JUST ACCESSED");
        } else {
            user = userRepository.findByEmail(email);
            if (user == null) {
                user = userRepository.findByPhone(email);
            }
        }

        if (user == null) {
            LOGGER.warn("User not found: " + email);
            throw new UsernameNotFoundException("Email: " + email + "was not found in the database.");
        }

        LOGGER.info("User: " + email + " just accessed");

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(user.getEmail(), user.getPassword(), grantedAuthorityList);
    }

    private Users initAdmin() {
        Users user = new Users();
        user.setUuid(UUID.randomUUID().toString().toUpperCase());
        user.setEmail(ADMIN_EMAIL);
        user.setPhone(ADMIN_PHONE);
        user.setPassword(ADMIN_PASSWORD);
        user.setRole(ADMIN_ROLE);
        user.setFirstName(ADMIN_NAME);
        return user;
    }
}
