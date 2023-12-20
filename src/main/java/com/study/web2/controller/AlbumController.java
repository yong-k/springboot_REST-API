package com.study.web2.controller;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.response.BaseResponse;
import com.study.web2.service.AlbumService;
import com.study.web2.vo.AlbumVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @PostMapping("/album")
    public BaseResponse<String> createAlbum(@RequestBody AlbumVo album) {
        try {
            albumService.createAlbum(album);
            return new BaseResponse<>("create success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in AlbumController.createAlbum()", e);
            return new BaseResponse<>(e);
        }
    }

    @GetMapping("/album")
    public BaseResponse<List<AlbumVo>> getAllAlbum(@RequestParam(required = false) Long userId) {
        return new BaseResponse<>(albumService.getAllAlbum(userId));
    }

    @GetMapping("/album/{id}")
    public BaseResponse<AlbumVo> getAlbumById(@PathVariable Long id) {
        try {
            AlbumVo album = albumService.getAlbumById(id);
            return new BaseResponse<>(album);
        } catch (DataNotFoundException e) {
            return new BaseResponse<>(e);
        } catch (Exception e) {
            log.error("Error in AlbumController.getAlbumById()", e);
            return new BaseResponse<>(e);
        }
    }

    @PutMapping("/album/{id}")
    public BaseResponse<String> updateAlbum(@PathVariable Long id, @RequestBody AlbumVo album) {
        try {
            album.setId(id);
            albumService.updateAlbum(album);
            return new BaseResponse<>("update success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in AlbumController.updateAlbum()", e);
            return new BaseResponse<>(e);
        }
    }

    @DeleteMapping("/album/{id}")
    public BaseResponse<String> deleteAlbum(@PathVariable Long id) {
        try {
            albumService.deleteAlbum(id);
            return new BaseResponse<>("delete success");
        } catch (Exception e) {
            log.error("Error in AlbumController.deleteAlbum()", e);
            return new BaseResponse<>(e);
        }
    }
}