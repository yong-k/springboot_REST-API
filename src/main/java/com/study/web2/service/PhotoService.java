package com.study.web2.service;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.mapper.PhotoMapper;
import com.study.web2.vo.PhotoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    public void createPhoto(PhotoVo photo) {
        photoMapper.createPhoto(photo);
    }

    public List<PhotoVo> getAllPhoto(int pageNum, int numOfRows, Long albumId, String title) {
        pageNum = numOfRows * (pageNum - 1);
        return photoMapper.getAllPhoto(pageNum, numOfRows, albumId, title);
    }

    public int countPhoto(Long albumId, String title) {
        return photoMapper.countPhoto(albumId, title);
    }

    public PhotoVo getPhotoById(long id) {
        PhotoVo photo = photoMapper.getPhotoById(id);
        if (photo == null)
            throw new DataNotFoundException("Not exist photo: id = " + id);
        return photo;
    }

    public void updatePhoto(PhotoVo photo) {
        int result = photoMapper.updatePhoto(photo);
        if (result < 1)
            throw new DataNotFoundException("[UPDATE fail] Not exist photo: id = " + photo.getId());
    }

    public void deletePhoto(long id) {
        int result = photoMapper.deletePhoto(id);
        if (result < 1)
            throw new DataNotFoundException("[DELETE fail] Not exist photo: id = " + id);
    }
}