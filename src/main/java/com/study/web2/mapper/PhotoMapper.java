package com.study.web2.mapper;

import com.study.web2.vo.PhotoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoMapper {

    int createPhoto(PhotoVo photo);

    List<PhotoVo> getAllPhoto(Long albumId);

    PhotoVo getPhotoById(Long id);

    int updatePhoto(PhotoVo photo);

    int deletePhoto(Long id);
}