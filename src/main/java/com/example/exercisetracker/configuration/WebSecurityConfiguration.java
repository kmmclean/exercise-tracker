package com.example.exercisetracker.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user@example.com").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/webjars/**", "/css/**", "/", "/about", "/register", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .csrf()
                .ignoringAntMatchers("/h2-console/**")
                .and()
            .headers()
                .frameOptions().sameOrigin()
                .and()
            .formLogin()
                .loginPage("/login").permitAll()
                .and()
            .logout()
                .logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
    }
}
