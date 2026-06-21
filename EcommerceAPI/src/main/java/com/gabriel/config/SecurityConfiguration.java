package com.gabriel.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@SecurityScheme(name = SecurityConfiguration.SECURITY, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme="bearer")
public class SecurityConfiguration {

 public static final String SECURITY="bearerAuth";;

 private final JwtAuthenticationFillter jwtAuthenticationFillter;


 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
     return http
             .csrf(AbstractHttpConfigurer::disable)
             .sessionManagement(session ->
                     session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/customers/**").permitAll()
                        .requestMatchers("/swagger-ui/**","/swagger-ui.html" ,"/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFillter, UsernamePasswordAuthenticationFilter.class)
                .build();
 }
 @Bean
 public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
 }
 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
     return authenticationConfiguration.getAuthenticationManager();

 }

}

