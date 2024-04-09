package com.learn.security.springsecurityone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUseDetailService customUseDetailService;

    //=========== Authentication ===========================
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//
//        UserDetails admin = User
//                .withUsername("Admin")
//                //.password("admin1234")
//                .password(passwordEncoder.encode("admin1234"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User
//                .withUsername("John")
//                .password(passwordEncoder.encode("john1234"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    //=============== Authorization =====================
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authRequest -> authRequest
                                .requestMatchers("/welcome")
                                .permitAll()
                                .requestMatchers("/products/**").authenticated()
                                .requestMatchers("/users/**").authenticated())
                //.httpBasic(Customizer.withDefaults()) //configures basic authentication
                .formLogin(Customizer.withDefaults()) //html form based login
                .build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUseDetailService);
        return authenticationProvider;

    }

}
