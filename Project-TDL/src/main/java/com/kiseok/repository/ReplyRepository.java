package com.kiseok.repository;

import com.kiseok.domain.Reply;
import com.kiseok.domain.ToDoList;
import com.kiseok.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
