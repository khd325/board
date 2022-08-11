package com.board.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostsSearchDto {

    private String searchBy;

    private String searchQuery = "";
}
