package com.study.web2.service;

import com.study.web2.mapper.PhotoMapper;
import com.study.web2.vo.PhotoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    PhotoMapper photoMapper;

    public void registerPhoto(PhotoVo photo) {
        photoMapper.registerPhoto(photo);
    }

    public List<PhotoVo> findAll(Long albumId) {
        return photoMapper.findAll(albumId);
    }

    public PhotoVo findById(Long id) {
        return photoMapper.findById(id);
    }

    public void updatePhoto(PhotoVo photo) {
        photoMapper.updatePhoto(photo);
    }

    public void deletePhoto(Long id) {
        photoMapper.deletePhoto(id);
    }
}