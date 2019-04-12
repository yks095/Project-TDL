package com.kiseok.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserDTO {

    @Pattern(regexp = "[0-9a-zA-Z]{5,20}")
    @NotBlank
    private String id;

    @Pattern(regexp = "[0-9a-zA-Z]{10,30}")
    @NotBlank
    private String password;

    @Email
    private String email;

    public User save(UserDTO userDTO)  {

        User user = new User();

        user.setId(userDTO.getId());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        System.out.println("진입 3");

        return user;
    }

}
