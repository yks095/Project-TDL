package com.kiseok.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Reply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    String content;

    @Column
    LocalDateTime createdDate;

    @Column
    LocalDateTime modifiedDate;

    @ManyToOne
    private ToDoList toDoList;

    @Builder
    public Reply(String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public void setCreatedDateNow() {
        this.createdDate = LocalDateTime.now();
    }



}
