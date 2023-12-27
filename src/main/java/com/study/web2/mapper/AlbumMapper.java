package com.study.web2.mapper;

import com.study.web2.vo.AlbumVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlbumMapper {

    int createAlbum(AlbumVo album);

    List<AlbumVo> getAllAlbum(int pageNum, int numOfRows, Long userId, String title);

    int countAlbum(Long userId, String title);

    AlbumVo getAlbumById(long id);

    int updateAlbum(AlbumVo album);

    int deleteAlbum(long id);
}