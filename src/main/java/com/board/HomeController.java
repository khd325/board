package com.board;

import com.board.entity.Posts;
import com.board.service.PostsService;
import com.board.web.dto.PostsListResponseDto;
import com.board.web.dto.PostsSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostsService postsService;

    @GetMapping({"/","/{page}"})
    public String home(PostsSearchDto postsSearchDto, @PathVariable("page")Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);

        Page<Posts> posts = postsService.getPostsPage(postsSearchDto, pageable);


//        List<PostsListResponseDto> postsListResponseDtos = postsService.findAllDesc();

//        model.addAttribute("postsListResponseDtos",postsListResponseDtos);
        model.addAttribute("posts",posts);
        model.addAttribute("postsSearchDto",postsSearchDto);
        model.addAttribute("maxPage",5);

        return "home";
    }
}
