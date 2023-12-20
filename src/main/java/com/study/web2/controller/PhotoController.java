package com.study.web2.controller;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.response.BaseResponse;
import com.study.web2.service.PhotoService;
import com.study.web2.vo.PhotoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @PostMapping("/photo")
    public BaseResponse<String> createPhoto(@RequestBody PhotoVo photo) {
        try {
            photoService.createPhoto(photo);
            return new BaseResponse<>("create success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in PhotoController.createPhoto()", e);
            return new BaseResponse<>(e);
        }
    }

    @GetMapping("/photo")
    public BaseResponse<List<PhotoVo>> getAllPhoto(@RequestParam(required = false) Long albumId) {
        return new BaseResponse<>(photoService.getAllPhoto(albumId));
    }

    @GetMapping("/photo/{id}")
    public BaseResponse<PhotoVo> getPhotoById(@PathVariable Long id) {
        try {
            PhotoVo photo = photoService.getPhotoById(id);
            return new BaseResponse<>(photo);
        } catch (DataNotFoundException e) {
            return new BaseResponse<>(e);
        } catch (Exception e) {
            log.error("Error in PhotoController.getPhotoById()", e);
            return new BaseResponse<>(e);
        }
    }

    @PutMapping("/photo/{id}")
    public BaseResponse<String> updatePhoto(@PathVariable Long id, @RequestBody PhotoVo photo) {
        try {
            photo.setId(id);
            photoService.updatePhoto(photo);
            return new BaseResponse<>("update success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in PhotoController.updatePhoto()", e);
            return new BaseResponse<>(e);
        }
    }

    @DeleteMapping("/photo/{id}")
    public BaseResponse<String> deletePhoto(@PathVariable Long id) {
        try {
            photoService.deletePhoto(id);
            return new BaseResponse<>("delete success");
        } catch (Exception e) {
            log.error("Error in PhotoController.deletePhoto()", e);
            return new BaseResponse<>(e);
        }
    }
}