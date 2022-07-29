package com.board.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
public class Posts extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "제목을 입력해주세요")
    @Column(columnDefinition = "TEXT",nullable = false)
    private String title;

    @NotNull(message = "내용을 입력해주세요")
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

}
