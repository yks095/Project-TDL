package com.kiseok.repository;

import com.kiseok.domain.Reply;
import com.kiseok.domain.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByToDoListOrderByIdx(ToDoList toDoList);

}
