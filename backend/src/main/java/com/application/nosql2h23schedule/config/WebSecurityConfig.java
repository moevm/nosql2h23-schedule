package com.application.nosql2h23schedule.config;

import com.application.nosql2h23schedule.service.AuthUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final AuthUserDetailService authUserDetailService;
    private final JwtFilter jwtFilter;

    @Autowired
    public WebSecurityConfig(AuthUserDetailService authUserDetailService, JwtFilter jwtFilter) {
        this.authUserDetailService = authUserDetailService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        return
                http
                        .csrf().disable()
                        .authorizeRequests()
//                        .antMatchers("/project/admin/*").hasRole("ADMIN")
                        .antMatchers("/schedule/auth/login").permitAll()
                        .antMatchers("/schedule/auth/register").permitAll()
                        .anyRequest().hasAnyRole("ADMIN")
                        .and()
                        .formLogin().loginPage("/schedule/auth/login")
                        .loginProcessingUrl("/schedule/auth/process_login")
                        .defaultSuccessUrl("/schedule/auth/smth", true)
                        .failureUrl("/schedule/auth/login")
                        .and()
                        .logout()
                        .logoutUrl("/schedule/auth/logout")
                        .logoutSuccessUrl("/schedule/auth/login")
                        .and()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .authenticationManager(authenticationManager)
                        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, AuthUserDetailService authUserDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authUserDetailService)
                .passwordEncoder(new BCryptPasswordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedHeaders(List.of("Accept", "Access-Control-Request-Method", "Access-Control-Request-Headers",
                "Accept-Language", "Authorization", "Content-Type", "Request-Name", "Request-Surname", "Origin", "X-Request-AppVersion",
                "X-Request-OsVersion", "X-Request-Device", "X-Requested-With"));
        configuration.setAllowedOrigins(List.of(
                "http://localhost:8080"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}