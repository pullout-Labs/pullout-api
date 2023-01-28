package com.pullout.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()

        http.authorizeHttpRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/healthcheck").permitAll()
            .antMatchers("/mypage").hasRole("USER")
            .anyRequest().authenticated()

        return http.build()
    }
}