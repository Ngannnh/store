//package ngocngan.store.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author ngan nnh on 5/27/2019
// * @project store
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////
////    private final BCryptPasswordEncoder bCryptPasswordEncoder;
////    private static UserRepository userRepository;
////    private static RoleRepository roleRepository;
////
////    @Autowired
////    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
////            UserRepository userRepository, RoleRepository roleRepository) {
////        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
////        WebSecurityConfig.userRepository = userRepository;
////        WebSecurityConfig.roleRepository = roleRepository;
////    }
////
////    @Bean public UserDetailsService postgreUserDetails() {
////        return new CustomUserDetailsService(userRepository, roleRepository);
////    }
////
////    @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        UserDetailsService userDetailsService = postgreUserDetails();
////
////        auth
////                .userDetailsService(userDetailsService)
////                .passwordEncoder(bCryptPasswordEncoder);
////    }
//
//    @Override protected void configure(HttpSecurity http) throws Exception{
//        http
//                .authorizeRequests()
//                .antMatchers("/**").permitAll();
//        // Access denied page config
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access-denied");
//    }
//}
