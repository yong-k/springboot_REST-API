package com.study.web2.mapper;

import com.study.web2.vo.PhotoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoMapper {

    int registerPhoto(PhotoVo photo);

    List<PhotoVo> findAll(Long albumId);

    PhotoVo findById(Long id);

    int updatePhoto(PhotoVo photo);

    int deletePhoto(Long id);
}