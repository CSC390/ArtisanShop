package csc394.artisanshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;//placeholder
import org.springframework.security.web.SecurityFilterChain;

import csc394.artisanshop.security.AuthEntryPoint;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;         


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http    
                .csrf().disable() //disable CSRF
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/", "/home").permitAll() //permit all to home page
                    .anyRequest().authenticated() //all other requests require authentication
                )
                .formLogin((formLogin) -> formLogin
                    .loginPage("/login") //login page
                    .permitAll() //permit all to login page
                )
                .logout((logout) -> logout.permitAll() //permit all to logout page
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }


    @Autowired
    private AuthEntryPoint authEntryPoint;
    
}