package com.kiseok.controller;

import com.kiseok.domain.ToDoList;
import com.kiseok.domain.User;
import com.kiseok.repository.UserRepository;
import com.kiseok.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/toDoList")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ToDoListService toDoListService;

    @GetMapping("/login")
    public String login()    {
        return "/toDoList/login";
    }

    @PostMapping("/login")
    public String loginPost()    {
        return "redirect:/toDoList/list";
    }

}
