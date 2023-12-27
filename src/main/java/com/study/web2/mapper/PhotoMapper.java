package com.study.web2.mapper;

import com.study.web2.vo.PhotoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoMapper {

    int createPhoto(PhotoVo photo);

    List<PhotoVo> getAllPhoto(int pageNum, int numOfRows, Long albumId, String title);

    int countPhoto(Long albumId, String title);

    PhotoVo getPhotoById(long id);

    int updatePhoto(PhotoVo photo);

    int deletePhoto(long id);
}