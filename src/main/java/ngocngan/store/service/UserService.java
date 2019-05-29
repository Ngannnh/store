package ngocngan.store.service;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Role;
import ngocngan.store.models.Users;
import ngocngan.store.repository.RoleRepository;
import ngocngan.store.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ngan nnh on 5/28/2019
 * @project store
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private static UserRepository userRepository;
    private static RoleRepository roleRepository;
    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        UserService.userRepository = userRepository;
        UserService.roleRepository = roleRepository;
        UserService.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // USER SERVICE ****************************************************************

    public List<Users> getUsers() {
        try {
            List<Users> users = userRepository.findAll();
            LOGGER.info(Constant.LOGGER_INFO_USERS_SIZE(users.size()));
            return users;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return null;
        }
    }

    public Users getUserByUUID(String uuid) {
        try {
            Users user = new Users();
            if (userRepository.findByUuid(uuid) != null) {
                user = userRepository.findByUuid(uuid);
                LOGGER.info(Constant.LOGGER_USER_FOUND(user.getUuid()));
            } else {
                LOGGER.warn(Constant.LOGGER_USER_NOT_FOUND(uuid));
            }
            return user;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return new Users();
        }
    }

    public Users saveUser(String uuid, String email, String password, String firstName, String lastName, String phone,
            String city, String address, String image, String role) {
        try {
            Users user = getUserByUUID(uuid);
            user.setUuid(uuid);
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setCity(city);
            user.setAddress(address);
            user.setImage(image);
            user.setRole(role);
            userRepository.save(user);
            LOGGER.info(Constant.LOGGER_SAVE_USER_SUCCESS(email));
            return user;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return null;
        }
    }

    public void deleteUser(String uuid) {
        try {
            userRepository.delete(getUserByUUID(uuid));
            LOGGER.info(Constant.LOGGER_DELETE_USER_SUCCESS(uuid));
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
        }
    }

    // ROLE SERVICE ****************************************************************

    public List<Role> getRoles() {
        try {
            List<Role> roles = roleRepository.findAll();
            LOGGER.info(Constant.LOGGER_INFO_ROLES_SIZE(roles.size()));
            return roles;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return null;
        }
    }

    public Role getRoleByUUID(String uuid) {
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

    public void saveRole(String uuid, String s) {
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

    public void deleteRole(String uuid) {
        try {
            roleRepository.delete(getRoleByUUID(uuid));
            LOGGER.info(Constant.LOGGER_DELETE_ROLE_SUCCESS(uuid));
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
        }
    }
}
