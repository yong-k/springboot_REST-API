package com.study.web2.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.dto.post.CreatePostReqDto;
import com.study.web2.dto.post.UpdatePostReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostVo {
    private Long id;
    private Long userId;
    private String title;
    private String body;

    public PostVo(CreatePostReqDto createPostReqDto) {
        this.userId = createPostReqDto.getUserId();
        this.title = createPostReqDto.getTitle();
        this.body = createPostReqDto.getBody();
    }

    public PostVo(UpdatePostReqDto updatePostReqDto) {
        this.title = updatePostReqDto.getTitle();
        this.body = updatePostReqDto.getBody();
    }
}