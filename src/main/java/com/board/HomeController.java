package com.board;

import com.board.service.PostsService;
import com.board.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostsService postsService;

    @GetMapping("/")
    public String home(Model model) {
        List<PostsListResponseDto> postsListResponseDtos = postsService.findAllDesc();

        model.addAttribute("postsListResponseDtos",postsListResponseDtos);
        return "home";
    }
}
