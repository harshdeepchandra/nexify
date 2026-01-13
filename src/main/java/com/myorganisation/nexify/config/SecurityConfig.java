package com.myorganisation.nexify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                                .anyRequest().authenticated()
                )
//                .formLogin(form -> form.permitAll())
//                .logout(logout -> logout.permitAll());
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}