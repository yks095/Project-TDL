package com.kiseok.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/", "/css/**", "/images/**", "/js/**",  "/toDoList/register",
                                 "/toDoList/api/register", "toDoList/api/list").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/toDoList/login")
                .successForwardUrl("/toDoList/login").permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/toDoList/login")
                .and()
                .csrf().disable();

}

    @Bean
    public PasswordEncoder passwordEncoder()    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
