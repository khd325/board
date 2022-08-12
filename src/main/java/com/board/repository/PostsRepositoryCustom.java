package com.board.repository;

import com.board.entity.Posts;
import com.board.web.dto.PostsListResponseDto;
import com.board.web.dto.PostsSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsRepositoryCustom {

    Page<PostsListResponseDto> getPostsPage(PostsSearchDto postsSearchDto, Pageable pageable);
}
