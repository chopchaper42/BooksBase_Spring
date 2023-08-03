package com.example.demo;

import com.example.demo.crud.UserRepository;
import com.example.demo.data.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User " + username + " not found.");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf((csrf) -> csrf.disable()) // TODO: enable csrf for security. Add a token to all post requests
            .authorizeHttpRequests(
                (authorizeHttpRequests) ->
                    authorizeHttpRequests
                        .requestMatchers("/", "/login", "/registration",
                                "/book", "/author", "/search", "/**", "/*").permitAll() //dunno if search is needed
//                        .requestMatchers("/favorite").hasRole("USER")
                ).formLogin((formLogin) -> {
                formLogin.loginPage("/login");
            });
        return http.build();
    }
}
