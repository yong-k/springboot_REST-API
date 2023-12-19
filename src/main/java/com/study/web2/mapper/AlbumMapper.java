package com.study.web2.mapper;

import com.study.web2.vo.AlbumVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlbumMapper {

    int registerAlbum(AlbumVo album);

    List<AlbumVo> findAll(Long userId);

    AlbumVo findById(Long id);

    int updateAlbum(AlbumVo album);

    int deleteAlbum(Long id);
}