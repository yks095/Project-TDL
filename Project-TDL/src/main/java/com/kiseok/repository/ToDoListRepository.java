package com.kiseok.repository;

import com.kiseok.domain.ToDoList;
import com.kiseok.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    List<ToDoList> findAllByUserOrderByIdx(User user);

//    ToDoList findByIdx(Long idx);

}
