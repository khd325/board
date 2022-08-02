package com.board.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Posts extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Builder
    public Posts(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}
