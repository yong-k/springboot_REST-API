package com.study.web2.mapper;

import com.study.web2.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int registerComment(CommentVo comment);

    List<CommentVo> findAll(Long postId);

    CommentVo findById(Long id);

    int updateComment(CommentVo comment);

    int deleteComment(Long id);
}