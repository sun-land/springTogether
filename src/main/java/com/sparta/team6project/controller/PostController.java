package com.sparta.team6project.controller;

import com.sparta.team6project.dto.PostRequestDto;
import com.sparta.team6project.dto.PostDetailResponseDto;
import com.sparta.team6project.dto.SuccessResponseDto;
import com.sparta.team6project.security.UserDetailsImpl;
import com.sparta.team6project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.sparta.team6project.dto.PostDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 게시글 작성 API
    @PostMapping("/post/write")
    public HashMap<String, Long> addPost(@RequestBody PostRequestDto postRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        System.out.println(postRequestDto.getTitle());
        System.out.println(postRequestDto.getContents());
        System.out.println(postRequestDto.getPostUser());

        return postService.addPost(postRequestDto, userDetails);

    }

    // 게시글 수정 API
    @PutMapping("/posts/{postId}")
    public HashMap<String, Long> editPost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.editPost(postId,postRequestDto,userDetails);

    }

    // 게시글 삭제 API
    @DeleteMapping("/posts/{postId}")
    public HashMap<String, Long> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.deletePost(postId, userDetails);


    }

    // 상세 게시글 조회 API
    @GetMapping("/getposts/{postId}")
    public PostDetailResponseDto getPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.getPost(postId, userDetails);
    }

    @GetMapping("/allposts")
    public PostDto getAllPost(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.getAllPost(userDetails);
    }
}
