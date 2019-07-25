package com.kiseok.controller;

import com.kiseok.domain.Reply;
import com.kiseok.domain.ReplyDTO;
import com.kiseok.domain.ToDoList;
import com.kiseok.repository.ReplyRepository;
import com.kiseok.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/toDoList")
public class ReplyController {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    ToDoListRepository toDoListRepository;

    @PostMapping("/api/reply/{idx}")
    public ResponseEntity<?> postReply(@PathVariable("idx")Long idx, @RequestBody Map<String, String> map)    {

        ToDoList toDoList = toDoListRepository.getOne(idx);
        Reply reply = new Reply();
        reply.setCreatedDateNow();
        reply.setToDoList(toDoList);
        reply.setContent(map.get("content"));
        replyRepository.save(reply);
        toDoList.add(reply);

        ReplyDTO replyDTO = new ReplyDTO(reply);

        return new ResponseEntity<>(replyDTO, HttpStatus.CREATED);
    }

    @PutMapping("/api/updateReply/{idx}")
    public ResponseEntity<?> putReply(@PathVariable("idx")Long idx, @RequestBody String content) {
        Reply persistReply= replyRepository.getOne(idx);

        persistReply.update(content);
        replyRepository.save(persistReply);

        ReplyDTO replyDTO = new ReplyDTO(persistReply);

        System.out.println(replyDTO.getIdx());

        return new ResponseEntity<>(replyDTO, HttpStatus.OK);
    }

    @DeleteMapping("/api/deleteReply/{idx}")
    public ResponseEntity<?> deleteReply(@PathVariable("idx")Long idx)  {

        System.out.println("삭제 메소드 진입");
        replyRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
