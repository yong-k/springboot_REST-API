package com.study.web2.service;

import com.study.web2.mapper.AlbumMapper;
import com.study.web2.vo.AlbumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumMapper albumMapper;

    public void registerAlbum(AlbumVo album) {
        albumMapper.registerAlbum(album);
    }

    public List<AlbumVo> findAll(Long userId) {
        return albumMapper.findAll(userId);
    }

    public AlbumVo findById(Long id) {
        return albumMapper.findById(id);
    }

    public void updateAlbum(AlbumVo album) {
        albumMapper.updateAlbum(album);
    }

    public void deleteAlbum(Long id) {
        albumMapper.deleteAlbum(id);
    }
}