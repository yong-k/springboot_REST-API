package com.study.web2.controller;

import com.study.web2.consts.ResultCode;
import com.study.web2.dto.CommonRespDto;
import com.study.web2.dto.photo.CreatePhotoReqDto;
import com.study.web2.dto.photo.GetAllPhotoRespDto;
import com.study.web2.dto.photo.GetPhotoRespDto;
import com.study.web2.dto.photo.UpdatePhotoReqDto;
import com.study.web2.exception.DataNotFoundException;
import com.study.web2.service.PhotoService;
import com.study.web2.vo.PhotoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping("/photo")
    public CommonRespDto createPhoto(@RequestBody CreatePhotoReqDto createPhotoReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            photoService.createPhoto(new PhotoVo(createPhotoReqDto));
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION);
            commonRespDto.setMessage("INSERT fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in PhotoController.createPhoto()", e);
        }
        return commonRespDto;
    }

    @GetMapping("/photo")
    public GetAllPhotoRespDto getAllPhoto(@RequestParam(required = false) Long albumId) {
        GetAllPhotoRespDto getAllPhotoRespDto = new GetAllPhotoRespDto();
        try {
            getAllPhotoRespDto.setPhotoList(photoService.getAllPhoto(albumId));
        } catch (Exception e) {
            getAllPhotoRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            getAllPhotoRespDto.setMessage("Unexpected Error");
            log.error("Error in PhotoController.getAllPhoto()", e);
        }
        return getAllPhotoRespDto;
    }

    @GetMapping("/photo/{id}")
    public GetPhotoRespDto getPhotoById(@PathVariable Long id) {
        GetPhotoRespDto getPhotoRespDto = new GetPhotoRespDto();
        try {
            getPhotoRespDto.setPhoto(photoService.getPhotoById(id));
        } catch (DataNotFoundException e) {
            getPhotoRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            getPhotoRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            getPhotoRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            getPhotoRespDto.setMessage("Unexpected Error");
            log.error("Error in PhotoController.getPhotoById()", e);
        }
        return getPhotoRespDto;
    }

    @PutMapping("/photo/{id}")
    public CommonRespDto updatePhoto(@PathVariable Long id, @RequestBody UpdatePhotoReqDto updatePhotoReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            PhotoVo photo = new PhotoVo(updatePhotoReqDto);
            photo.setId(id);
            photoService.updatePhoto(photo);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION);
            commonRespDto.setMessage("UPDATE fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in PhotoController.updatePhoto()", e);
        }
        return commonRespDto;
    }

    @DeleteMapping("/photo/{id}")
    public CommonRespDto deletePhoto(@PathVariable Long id) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            photoService.deletePhoto(id);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in PhotoController.deletePhoto()", e);
        }
        return commonRespDto;
    }
}