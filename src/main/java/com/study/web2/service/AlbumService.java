package com.study.web2.service;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.mapper.AlbumMapper;
import com.study.web2.vo.AlbumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumMapper albumMapper;

    public void createAlbum(AlbumVo album) {
        albumMapper.createAlbum(album);
    }

    public List<AlbumVo> getAllAlbum(Long userId) {
        return albumMapper.getAllAlbum(userId);
    }

    public AlbumVo getAlbumById(Long id) {
        AlbumVo album = albumMapper.getAlbumById(id);
        if (album == null)
            throw new DataNotFoundException("Not exist album: id = " + id);
        return album;
    }

    public void updateAlbum(AlbumVo album) {
        albumMapper.updateAlbum(album);
    }

    public void deleteAlbum(Long id) {
        albumMapper.deleteAlbum(id);
    }
}