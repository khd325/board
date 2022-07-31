package com.board.service;

import com.board.entity.Posts;
import com.board.repository.PostsRepository;
import com.board.web.dto.PostsFormRequestDto;
import com.board.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {

        return postsRepository.findAllDesc().stream().
                map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long savePosts(PostsFormRequestDto postsFormRequestDto) {
        Posts posts = postsFormRequestDto.toEntity();
        postsRepository.save(posts);

        return posts.getId();
    }


}
