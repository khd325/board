package com.board.controller;


import com.board.service.PostsService;
import com.board.web.dto.PostsFormRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/save")
    public String postsForm(Model model){
        model.addAttribute("postsFormRequestDto",new PostsFormRequestDto());
        return "posts/addPosts";
    }

    @PostMapping("/save")
    public String postsSave(@Valid @ModelAttribute PostsFormRequestDto postsFormRequestDto, BindingResult bindingResult, Model model){

        log.info("save posts : {}",postsFormRequestDto.getTitle());
        if(bindingResult.hasErrors()){
            return "posts/addPosts";
        }

        try {
            postsService.savePosts(postsFormRequestDto);
        } catch (Exception e){
            model.addAttribute("errorMessage", "등록 중 에러가 발생 했습니다.");
            return "posts/addPosts";
        }

        return "redirect:/";
    }
}
