package com.board.repository;

import com.board.entity.Posts;
import com.board.entity.QPosts;
import com.board.web.dto.PostsSearchDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class PostsRepositoryCustomImpl implements PostsRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public PostsRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Posts> getPostsPage(PostsSearchDto postsSearchDto, Pageable pageable) {
        List<Posts> content = queryFactory
                .selectFrom(QPosts.posts)
                .where(searchByLike(postsSearchDto.getSearchBy(), postsSearchDto.getSearchQuery()))
                .orderBy(QPosts.posts.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(QPosts.posts.count())
                .from(QPosts.posts)
                .where(searchByLike(postsSearchDto.getSearchBy(), postsSearchDto.getSearchQuery()))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    BooleanExpression searchByLike(String searchBy, String searchQuery) {

        if (StringUtils.equals("title", searchBy)) {
            return QPosts.posts.title.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("writer", searchBy)) {
            return QPosts.posts.title.like("%" + searchQuery + "%");
        }

        return null;
    }
}
