package com.unibuc.bookmanagement.config;

import com.unibuc.bookmanagement.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@Profile("!test") // se aplica în toate profilele, exceptand 'test'
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/users/register",
                "/users/login",
                "/css/**",
                "/js/**",
                "/images/**"
            ).permitAll()

            // acces public pentru listă cărți, detalii și căutare
            .requestMatchers("/books", "/books/{id}", "/books/find").permitAll()

            // doar adminul poate adăuga cărți sau autori
            .requestMatchers("/books/add", "/authors/add").hasRole("ADMIN")

            // orice altceva din /books/** (ex: delete) → doar logați
            .requestMatchers("/books/**").authenticated()

            // restul aplicației → necesită autentificare
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/users/login")  // ruta GET către pagina de login
            .loginProcessingUrl("/login") // ruta POST de autentificare
            .defaultSuccessUrl("/books", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/users/login?logout")
            .permitAll()
        );

    return http.build();
}



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}