package com.board.web.dto;

import com.board.entity.Posts;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;


@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String createdDate;


    public PostsListResponseDto(Posts posts){
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.writer = posts.getWriter();
        this.createdDate = posts.getCreatedDate();
    }

    @QueryProjection
    public PostsListResponseDto(Long id, String title, String writer, String createdDate){
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createdDate = createdDate;
    }
}
