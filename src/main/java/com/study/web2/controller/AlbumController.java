package com.study.web2.controller;

import com.study.web2.service.AlbumService;
import com.study.web2.vo.AlbumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @PostMapping("/album")
    public void registerAlbum(@RequestBody AlbumVo album) {
        albumService.registerAlbum(album);
    }

    @GetMapping("/album")
    public List<AlbumVo> findAll(@RequestParam(required = false) Long userId) {
        return albumService.findAll(userId);
    }

    @GetMapping("/album/{id}")
    public AlbumVo findById(@PathVariable Long id) {
        return albumService.findById(id);
    }

    @PutMapping("/album/{id}")
    public void updateAlbum(@PathVariable Long id, @RequestBody AlbumVo album) {
        album.setId(id);
        albumService.updateAlbum(album);
    }

    @DeleteMapping("/album/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
    }
}