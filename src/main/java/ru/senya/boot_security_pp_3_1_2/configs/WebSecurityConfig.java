package ru.senya.boot_security_pp_3_1_2.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.senya.boot_security_pp_3_1_2.service.UserServiceImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserServiceImp userServiceImp;

    private final SuccessUserHandler successUserHandler;

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(
            UserServiceImp userServiceImp,
            SuccessUserHandler successUserHandler,
            PasswordEncoder passwordEncoder) {

        this.userServiceImp = userServiceImp;
        this.successUserHandler = successUserHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler).permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImp).passwordEncoder(passwordEncoder);

    }

}
