package ngocngan.store.config;

import ngocngan.store.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author ngan nnh on 5/27/2019
 * @project store
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static UserDetailsServiceImpl userDetailsService;
    private static DataSource dataSource;

    @Autowired public WebSecurityConfig(UserDetailsServiceImpl customUserDetailsService) {
        WebSecurityConfig.userDetailsService = customUserDetailsService;
    }

    @Bean public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // The pages has'n to login
        http.authorizeRequests().antMatchers("/", "/login", "/logout", "/register", "/access-denied").permitAll();

        // Only ADMIN role
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().hasRole("ADMIN");

        // Only ADMIN role
        http.authorizeRequests().antMatchers("/admin/users").hasAuthority("CUSTOMER").anyRequest().hasRole("CUSTOMER");

        // Config access denied page
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access-denied");

        // Config for log in form
        http.authorizeRequests().and().formLogin().loginProcessingUrl("/login-form").loginPage("/login")
                .defaultSuccessUrl("/admin").failureUrl("/login?error=true").usernameParameter("email")
                .passwordParameter("password");

        // Config for log out
        http.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/").and().exceptionHandling();
    }

    @Override public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/favicon.ico", "/customer/**", "/customer/css/**", "/customer/css/bootstrap-4.1.3/**",
                        "/customer/font/**", "/customer/js/**", "/admin/css/**", "/admin/font/**", "/admin/js/**");
    }
}
