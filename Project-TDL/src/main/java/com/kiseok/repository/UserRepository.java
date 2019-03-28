package com.kiseok.repository;

import com.kiseok.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(String id);
//    Long findByIdx(Long idx);
}
