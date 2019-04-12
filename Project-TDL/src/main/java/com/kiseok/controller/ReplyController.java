package com.kiseok.controller;

import com.kiseok.domain.Reply;
import com.kiseok.domain.ToDoList;
import com.kiseok.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/toDoList")
public class ReplyController {

    @Autowired
    ReplyRepository replyRepository;

    private ToDoList toDoList;


    @PostMapping("/api/reply")
    public ResponseEntity<?> postReply(@RequestBody Reply reply)    {

        reply.setCreatedDateNow();
        reply.setToDoList(this.toDoList);
        this.toDoList.add(reply);
        System.out.println(toDoList);

        System.out.println(reply);
        replyRepository.save(reply);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

}
