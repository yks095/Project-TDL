package com.kiseok.controller;

import com.kiseok.domain.User;
import com.kiseok.domain.UserDTO;
import com.kiseok.repository.UserRepository;
import com.kiseok.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/toDoList")
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ToDoListService toDoListService;

    @GetMapping("/register")
    public String register() {
        return "/toDoList/register";
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO)    {
        toDoListService.save(userDTO.save(userDTO));
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PostMapping("/check/register")
    public ResponseEntity<?> checkUser(@RequestBody  Map<String, String> map) {

        String id = map.get("id");
        User checkUser = userRepository.findById(id);

        if (checkUser == null) {
            return new ResponseEntity<>("{}", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
    }




}
