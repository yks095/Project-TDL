package com.kiseok.controller;

import com.kiseok.domain.ToDoList;
import com.kiseok.domain.User;
import com.kiseok.repository.UserRepository;
import com.kiseok.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/toDoList")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ToDoListService toDoListService;

    private User user;



    @GetMapping("/login")
    public String login()    {
        return "/toDoList/login";
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> user) {

        String id = user.get("id");
        String password = user.get("password");

        if (toDoListService.loginCheck(id, password))   {
            System.out.println("id" + id + ", password" + password);
            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
        else return null;
    }




}
