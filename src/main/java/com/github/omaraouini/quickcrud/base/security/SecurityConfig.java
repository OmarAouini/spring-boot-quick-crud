package com.github.omaraouini.quickcrud.base.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author aouin
 * Date: 18/01/2025
 * Time: 12:59
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Profile("local")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                //.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .httpBasic(withDefaults())
                .build();
    }

    /*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
        return web -> web.antMatchers("/ignore1", "/ignore2");
    }

     */
}