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
    private AlbumMapper albumMapper;

    public void createAlbum(AlbumVo album) {
        albumMapper.createAlbum(album);
    }

    public List<AlbumVo> getAllAlbum(int pageNum, int numOfRows, Long userId, String title) {
        pageNum = numOfRows * (pageNum - 1);
        return albumMapper.getAllAlbum(pageNum, numOfRows, userId, title);
    }

    public int countAlbum(Long userId, String title) {
        return albumMapper.countAlbum(userId, title);
    }

    public AlbumVo getAlbumById(Long id) {
        AlbumVo album = albumMapper.getAlbumById(id);
        if (album == null)
            throw new DataNotFoundException("Not exist album: id = " + id);
        return album;
    }

    public void updateAlbum(AlbumVo album) {
        int result = albumMapper.updateAlbum(album);
        if (result < 1)
            throw new DataNotFoundException("[UPDATE fail] Not exist album: id = " + album.getId());
    }

    public void deleteAlbum(Long id) {
        int result = albumMapper.deleteAlbum(id);
        if (result < 1)
            throw new DataNotFoundException("[DELETE fail] Not exist album: id = " + id);
    }
}