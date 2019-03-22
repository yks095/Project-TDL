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

    public List<ToDoList> findTdlList() {
        return toDoListRepository.findAllByOrderByIdx();
    }

    public ToDoList findTdlByIdx(Long idx)   {
        return toDoListRepository.findById(idx).orElse(new ToDoList());
    }

    public User findUserByIdx() {
        return userRepository.getOne(1L);
    }

    public boolean loginCheck(String id, String password)  {
        /*
        1. 디비에서 user를 찾았는데 없는 경우
        2. 디비에서 user를 찾았느데 비밀번호가 다를경우
        3. 디비에서 user를 찾고 비밀번호가 같은 경우
         */

        System.out.println(id + " " + password);

        User selectUser = userRepository.findById(id);

        System.out.println(selectUser);

        if(selectUser == null)
            return false;

        return selectUser.getPassword().equals(password);

    }
}
