package com.kiseok.controller;

import com.kiseok.repository.UserRepository;
import com.kiseok.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
