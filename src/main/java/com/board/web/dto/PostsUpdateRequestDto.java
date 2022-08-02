package com.board.web.dto;

import com.board.entity.Posts;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@Setter
public class PostsUpdateRequestDto {

    @NotEmpty(message = "제목을 입력해주세요")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요")
    private String content;

    PostsUpdateRequestDto(){

    }

    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
