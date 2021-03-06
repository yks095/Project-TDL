package com.kiseok.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class ToDoList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String description;

    @Column
    private Boolean status;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime completedDate;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "toDoList", fetch = FetchType.EAGER)
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public ToDoList(String description, Boolean status, LocalDateTime createdDate, LocalDateTime completedDate) {
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.completedDate = completedDate;
    }

    public void setCreatedDateNow() {
        this.status = false;
        this.createdDate = LocalDateTime.now();
    }

    public void update(String description)    {
        this.description = description;
    }

    public void updateStatus()    {
       this.status = !this.getStatus();
       this.completedDate = this.status? LocalDateTime.now() : null;
    }

    public void add(Reply reply) {
        reply.setToDoList(this);
        this.replies.add(reply);
    }


}