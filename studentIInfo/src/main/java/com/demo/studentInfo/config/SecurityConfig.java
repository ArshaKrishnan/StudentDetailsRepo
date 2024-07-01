package com.demo.studentInfo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;



@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
                http.csrf(csrf-> csrf.disable())
                        .cors(cors-> cors.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/getAllStudents","/getStudentById/**","/createStudent/**","/updateById/**","/deleteById/**").authenticated() // Restrict access to certain endpoints
                        .anyRequest().permitAll())
                        .httpBasic(withDefaults())
                        .formLogin(withDefaults());
        // @formatter:on
        return http.build();
    }

    // @formatter:off
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
