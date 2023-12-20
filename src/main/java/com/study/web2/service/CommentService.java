package com.study.web2.service;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.mapper.CommentMapper;
import com.study.web2.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    public void createComment(CommentVo comment) {
        commentMapper.createComment(comment);
    }

    public List<CommentVo> getAllComment(Long postId) {
        return commentMapper.getAllComment(postId);
    }

    public CommentVo getCommentById(Long id) {
        CommentVo comment = commentMapper.getCommentById(id);
        if (comment == null)
            throw new DataNotFoundException("Not exist comment: id = " + id);
        return comment;
    }

    public void updateComment(CommentVo comment) {
        commentMapper.updateComment(comment);
    }

    public void deleteComment(Long id) {
        commentMapper.deleteComment(id);
    }
}