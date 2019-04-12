package com.kiseok.service;

import com.kiseok.domain.Reply;
import com.kiseok.domain.ToDoList;
import com.kiseok.domain.User;
import com.kiseok.repository.ReplyRepository;
import com.kiseok.repository.ToDoListRepository;
import com.kiseok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoListService implements UserDetailsService {

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<ToDoList> findTdlList(User user) {

        return toDoListRepository.findAllByUserOrderByIdx(user);
    }

    public User findUserByIdx() {
        return userRepository.getOne(1L);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findById(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails
                .User(user.getId(), user.getPassword(), authorities);

    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
