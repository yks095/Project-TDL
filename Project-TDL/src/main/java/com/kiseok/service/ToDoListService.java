package com.kiseok.service;

import com.kiseok.domain.ToDoList;
import com.kiseok.domain.User;
import com.kiseok.repository.ToDoListRepository;
import com.kiseok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    UserRepository userRepository;

    private User user;

    public List<ToDoList> findTdlList(User user) {
        return toDoListRepository.findAllByUserOrderByIdx(user);
    }

//    public ToDoList findTdlByIdx(Long idx)   {
//        return toDoListRepository.findById(idx).orElse(new ToDoList());
//    }

    public User findUserByIdx() {
        return userRepository.getOne(1L);
    }

    public boolean loginCheck(String id, String password)  {

        System.out.println(id + " " + password);

        User selectUser = userRepository.findById(id);

        System.out.println(selectUser);

        if(selectUser == null)
            return false;

        return selectUser.getPassword().equals(password);

    }
}
