package com.kiseok.controller;

import com.kiseok.domain.ToDoList;
import com.kiseok.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/toDoLists")
public class ToDoListRestController {

    @Autowired
    ToDoListRepository toDoListRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getToDoLists()    {
        List<ToDoList> toDoLists = toDoListRepository.findAll();

        return ResponseEntity.ok(toDoLists);
    }

    @PostMapping
    public ResponseEntity<?> postToDoList(@RequestBody ToDoList toDoList)    {

        toDoList.setCreatedDateNow();
        toDoListRepository.save(toDoList);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @PutMapping("update/{idx}")
    public ResponseEntity<?> putToDoList(@PathVariable("idx")Long idx, @RequestBody String description) {
        System.out.println("Dfasdfsafsadf");
        ToDoList persistToDoList= toDoListRepository.getOne(idx);
        persistToDoList.update(description);
        toDoListRepository.save(persistToDoList);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PutMapping("status/{idx}")
    public ResponseEntity<?> statusToDoList(@PathVariable("idx")Long idx) {
        ToDoList statusToDoList= toDoListRepository.getOne(idx);
        statusToDoList.updateStatus();
        toDoListRepository.save(statusToDoList);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("{idx}")
    public ResponseEntity<?> deleteToDoList(@PathVariable("idx")Long idx)  {
        toDoListRepository.deleteById(idx);
        toDoListRepository.findAllByOrderByIdx();
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
