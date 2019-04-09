package com.kiseok.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Setter
@ToString
@Getter
@NoArgsConstructor
@Entity
@Table
public class User implements Serializable {

//    public void add2(UserDTO userDTO) throws ClassNotFoundException, SQLException   {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jpa_ex");
//
//        PreparedStatement ps = connection.prepareStatement()
//    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ToDoList> toDoLists = new ArrayList<>();

    @Builder
    public User(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public void add(ToDoList toDoList)  {
        toDoList.setUser(this);
        this.toDoLists.add(toDoList);
    }
}