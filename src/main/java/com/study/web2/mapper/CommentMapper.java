package com.study.web2.mapper;

import com.study.web2.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int createComment(CommentVo comment);

    List<CommentVo> getAllComment(Long postId);

    CommentVo getCommentById(Long id);

    int updateComment(CommentVo comment);

    int deleteComment(Long id);
}