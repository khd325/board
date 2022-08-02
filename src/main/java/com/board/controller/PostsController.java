package com.board.controller;


import com.board.service.PostsService;
import com.board.web.dto.PostsFormRequestDto;
import com.board.web.dto.PostsResponseDto;
import com.board.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/save")
    public String postsForm(Model model) {
        model.addAttribute("posts", new PostsFormRequestDto());
        return "posts/addPosts";
    }

    @PostMapping("/save")
    public String postsSave(@Valid @ModelAttribute PostsFormRequestDto postsFormRequestDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "posts/addPosts";
        }

        try {
            postsService.savePosts(postsFormRequestDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "등록 중 에러가 발생 했습니다.");
            return "posts/addPosts";
        }

        return "redirect:/";
    }

    @GetMapping("/{postsId}")
    public String postsDetail(@PathVariable("postsId") Long postsId, Model model) {
        PostsResponseDto postsResponseDto = postsService.findById(postsId);
        model.addAttribute("posts", postsResponseDto);

        return "posts/postsDetail";
    }

    @DeleteMapping("/{postsId}")
    @ResponseBody
    public Long postsDetail(@PathVariable("postsId") Long postsId) {
        postsService.deletePosts(postsId);
        return postsId;
    }

    @GetMapping("/update/{postsId}")
    public String postsUpdateForm(@PathVariable("postsId") Long postsId, Model model) {
        PostsResponseDto postsResponseDto = postsService.findById(postsId);
        model.addAttribute("posts", postsResponseDto);
        log.info("get method id = {} ", postsResponseDto.getId());
        return "posts/updateForm";
    }

    @PutMapping("/update/{postsId}")
    @ResponseBody
    public Long update(@PathVariable("postsId") Long postsId,
                       @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
//
//        try {
//            Long id = postsService.updatePosts(postsId, postsUpdateRequestDto);
//        } catch(Exception e){
//
//            model.addAttribute("errorMessage",e.getMessage());
//
//        }

        return postsService.updatePosts(postsId, postsUpdateRequestDto);
    }
}
