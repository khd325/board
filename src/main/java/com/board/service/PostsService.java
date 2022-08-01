package com.board.service;

import com.board.entity.Posts;
import com.board.repository.PostsRepository;
import com.board.web.dto.PostsFormRequestDto;
import com.board.web.dto.PostsListResponseDto;
import com.board.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글 입니다."));

        return new PostsResponseDto(posts);
    }


}
