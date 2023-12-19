package com.study.web2.controller;

import com.study.web2.service.PhotoService;
import com.study.web2.vo.PhotoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @PostMapping("/photo")
    public void registerPhoto(@RequestBody PhotoVo photo) {
        photoService.registerPhoto(photo);
    }

    @GetMapping("/photo")
    public List<PhotoVo> findAll(@RequestParam(required = false) Long albumId) {
        return photoService.findAll(albumId);
    }

    @GetMapping("/photo/{id}")
    public PhotoVo findById(@PathVariable Long id) {
        return photoService.findById(id);
    }

    @PutMapping("/photo/{id}")
    public void updatePhoto(@PathVariable Long id, @RequestBody PhotoVo photo) {
        photo.setId(id);
        photoService.updatePhoto(photo);
    }

    @DeleteMapping("/photo/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
    }
}