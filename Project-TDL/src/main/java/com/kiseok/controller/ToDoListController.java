package com.kiseok.controller;

import com.kiseok.domain.ToDoList;
import com.kiseok.domain.User;
import com.kiseok.repository.ToDoListRepository;
import com.kiseok.repository.UserRepository;
import com.kiseok.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/toDoList")
public class ToDoListController {

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    ToDoListService toDoListService;

    @Autowired
    UserRepository userRepository;

    private User user;

    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
//        User user = userRepository.getOne(1L);

        model.addAttribute("toDoList", toDoListService.findTdlByIdx(idx));
        return "/toDoList/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        user = toDoListService.findUserByIdx();
        model.addAttribute("tdlList", toDoListService.findTdlList());
        return "/toDoList/list";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getToDoLists()    {
        List<ToDoList> toDoLists = toDoListRepository.findAll();

        return ResponseEntity.ok(toDoLists);
    }

    @PostMapping("/api/list")
    public ResponseEntity<?> postToDoList(@RequestBody ToDoList toDoList)    {

        toDoList.setCreatedDateNow();
        toDoList.setUser(user);
        toDoListRepository.save(toDoList);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @PutMapping("/api/update/{idx}")
    public ResponseEntity<?> putToDoList(@PathVariable("idx")Long idx, @RequestBody String description) {
        ToDoList persistToDoList= toDoListRepository.getOne(idx);
        persistToDoList.update(description);
        toDoListRepository.save(persistToDoList);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PutMapping("/api/status/{idx}")
    public ResponseEntity<?> statusToDoList(@PathVariable("idx")Long idx) {
        ToDoList statusToDoList= toDoListRepository.getOne(idx);
        statusToDoList.updateStatus();
        toDoListRepository.save(statusToDoList);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/api/delete/{idx}")
    public ResponseEntity<?> deleteToDoList(@PathVariable("idx")Long idx)  {
        toDoListRepository.deleteById(idx);
        toDoListRepository.findAllByOrderByIdx();
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }



}
