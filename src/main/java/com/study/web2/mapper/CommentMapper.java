package com.study.web2.mapper;

import com.study.web2.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int createComment(CommentVo comment);

    List<CommentVo> getAllComment(int pageNum, int numOfRows, Long postId, String name, String email, String body);

    int countComment(Long postId, String name, String email, String body);

    CommentVo getCommentById(Long id);

    int updateComment(CommentVo comment);

    int deleteComment(Long id);
}