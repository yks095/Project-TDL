package com.kiseok;

import com.kiseok.domain.ToDoList;
import com.kiseok.domain.User;
import com.kiseok.repository.ToDoListRepository;
import com.kiseok.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class TdlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TdlApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository) throws Exception    {
        return (args) -> {
            User user = userRepository.save(User.builder()
                            .id("yang")
                            .password("1234")
                            .email("rltjr219@gmail.com")
                            .build());
        };
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }
}