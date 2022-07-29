package com.board.repository;

import com.board.entity.Posts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostsRepositoryTest {

    @Autowired PostsRepository postsRepository;

    @PersistenceContext
    EntityManager em;


    @Test
    void createPosts() {

        Posts posts = new Posts();
        posts.setTitle("title");
        posts.setContent("content");
        posts.setWriter("writer");

        Posts savedPosts = postsRepository.save(posts);

        em.flush();
        em.clear();

        Assertions.assertThat(savedPosts.getTitle()).isEqualTo(posts.getTitle());
        Assertions.assertThat(savedPosts.getContent()).isEqualTo(posts.getContent());
        Assertions.assertThat(savedPosts.getWriter()).isEqualTo(posts.getWriter());
    }



}