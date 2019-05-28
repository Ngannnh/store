//package ngocngan.store.service;
//
//import ngocngan.store.models.Role;
//import ngocngan.store.models.Users;
//import ngocngan.store.repository.RoleRepository;
//import ngocngan.store.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
///**
// * @author ngan nnh on 5/27/2019
// * @project store
// */
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//
//    @Autowired public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }
//
//    @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Users user = userRepository.findByEmail(email);
//        if(user!=null){
//            GrantedAuthority grantedAuthority = getGrantedAuthority(roleRepository.findByRole(user.getRole()));
//            return buildUserDetailsForAuthentication(user,grantedAuthority);
//        }else {
//            throw new UsernameNotFoundException("User not found");
//        }
//    }
//
//    private GrantedAuthority getGrantedAuthority(Role role){
//        return new SimpleGrantedAuthority(role.getRole());
//    }
//
//    private UserDetails buildUserDetailsForAuthentication(Users user, GrantedAuthority authority){
//        return new User(user.getEmail(),user.getPassword(), Collections.singleton(authority));
//    }
//}
