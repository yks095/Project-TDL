package com.kiseok.controller;

import com.kiseok.domain.Reply;
import com.kiseok.domain.ToDoList;
import com.kiseok.repository.ReplyRepository;
import com.kiseok.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/toDoList")
public class ReplyController {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    ToDoListRepository toDoListRepository;

    @PostMapping("/api/reply/{idx}")
    public ResponseEntity<?> postReply(@PathVariable("idx")Long idx, @RequestBody Reply reply)    {

        ToDoList toDoList = toDoListRepository.getOne(idx);
        reply.setCreatedDateNow();
        reply.setToDoList(toDoList);
        toDoList.add(reply);
        replyRepository.save(reply);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @PutMapping("/api/updateReply/{idx}")
    public ResponseEntity<?> putReply(@PathVariable("idx")Long idx, @RequestBody String content) {
        Reply persistReply= replyRepository.getOne(idx);
        persistReply.update(content);
        replyRepository.save(persistReply);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/api/deleteReply/{idx}")
    public ResponseEntity<?> deleteReply(@PathVariable("idx")Long idx)  {
        replyRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
