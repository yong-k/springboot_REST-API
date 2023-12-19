package com.study.web2.service;

import com.study.web2.mapper.CommentMapper;
import com.study.web2.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    public void registerComment(CommentVo comment) {
        commentMapper.registerComment(comment);
    }

    public List<CommentVo> findAll(Long postId) {
        return commentMapper.findAll(postId);
    }

    public CommentVo findById(Long id) {
        return commentMapper.findById(id);
    }

    public void updateComment(CommentVo comment) {
        commentMapper.updateComment(comment);
    }

    public void deleteComment(Long id) {
        commentMapper.deleteComment(id);
    }
}