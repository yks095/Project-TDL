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

    @PutMapping("{idx}")
    public ResponseEntity<?> putToDoList(@PathVariable("idx")Long idx, @RequestBody ToDoList toDoList) {
        System.out.println("ddddd");
        ToDoList persistToDoList= toDoListRepository.getOne(idx);
        persistToDoList.update(toDoList);
        toDoListRepository.save(persistToDoList);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

//    @DeleteMapping("{idx}")
//    public ResponseEntity<?> deleteBoard(@PathVariable("idx")Long idx)  {
//        boardRepository.deleteById(idx);
//
//        return new ResponseEntity<>("{}", HttpStatus.OK);
//    }
}
