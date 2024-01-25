package com.example.demo.config;

import com.example.demo.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@CrossOrigin("*")
public class WebSecurityConfig {

    // Настройка доступа к страницам
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // Установили страницу авторизации и выдали на ее получение для всех
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/profile") // Стандартный адресс перенаправления после успешной авторизации
                )

                .logout((logout) -> logout.permitAll()) // Доступ для всех для выхода из аккаунта
                // Настройка доступа
                .authorizeHttpRequests((requests) -> requests
                        // Выдали всем жоступ к этим страницам
                        .requestMatchers("/", "/welcome",  "/static/js/**", "/add", "/static/img/**",
                                "/static/css/**", "/getProfile", "/courses",
                                "/files/list", "/files/**")
                        .permitAll()
                        // Страницу авторизации сделали доступной только для неавторизованных пользователей
                        .requestMatchers("/registration").anonymous()
                        // Сделали страницу администратора доступной только пользователем с ролью ADMIN
                        .requestMatchers("/admin", "/listOfUsers"  ).hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                );

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
    // Настроили свой провайдер аутентификации
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    //  Задали кодировщик для защиты паролей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


