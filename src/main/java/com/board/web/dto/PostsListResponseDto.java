package com.board.web.dto;

import com.board.entity.Posts;
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
}
