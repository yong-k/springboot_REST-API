package com.study.web2.controller;

import com.study.web2.consts.ResultCode;
import com.study.web2.dto.CommonRespDto;
import com.study.web2.dto.album.CreateAlbumReqDto;
import com.study.web2.dto.album.GetAlbumRespDto;
import com.study.web2.dto.album.GetAllAlbumRespDto;
import com.study.web2.dto.album.UpdateAlbumReqDto;
import com.study.web2.exception.DataNotFoundException;
import com.study.web2.service.AlbumService;
import com.study.web2.utils.Pagination;
import com.study.web2.vo.AlbumVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/album")
    public CommonRespDto createAlbum(@RequestBody CreateAlbumReqDto createAlbumReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            albumService.createAlbum(new AlbumVo(createAlbumReqDto));
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION.value());
            commonRespDto.setMessage("INSERT fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in AlbumController.createAlbum()", e);
        }
        return commonRespDto;
    }

    @GetMapping("/album")
    public GetAllAlbumRespDto getAllAlbum(@RequestParam(required = false) Long userId,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false, defaultValue = "1") int pageNum,
                                          @RequestParam(required = false, defaultValue = "10") int numOfRows) {
        GetAllAlbumRespDto getAllAlbumRespDto = new GetAllAlbumRespDto();
        try {
            int totalDataCount = albumService.countAlbum(userId, title);
            getAllAlbumRespDto.setPage(new Pagination(totalDataCount, numOfRows, pageNum));
            getAllAlbumRespDto.setAlbumList(albumService.getAllAlbum(pageNum, numOfRows, userId, title));
        } catch (Exception e) {
            getAllAlbumRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            getAllAlbumRespDto.setMessage("Unexpected Error");
            log.error("Error in AlbumController.getAllAlbum()", e);
        }
        return getAllAlbumRespDto;
    }

    @GetMapping("/album/{id}")
    public GetAlbumRespDto getAlbumById(@PathVariable long id) {
        GetAlbumRespDto getAlbumRespDto = new GetAlbumRespDto();
        try {
            getAlbumRespDto.setAlbum(albumService.getAlbumById(id));
        } catch (DataNotFoundException e) {
            getAlbumRespDto.setCode(ResultCode.DATA_NOT_FOUND.value());
            getAlbumRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            getAlbumRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            getAlbumRespDto.setMessage("Unexpected Error");
            log.error("Error in AlbumController.getAlbumById()", e);
        }
        return getAlbumRespDto;
    }

    @PutMapping("/album/{id}")
    public CommonRespDto updateAlbum(@PathVariable long id, @RequestBody UpdateAlbumReqDto updateAlbumReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            AlbumVo album = new AlbumVo(updateAlbumReqDto);
            album.setId(id);
            albumService.updateAlbum(album);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND.value());
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION.value());
            commonRespDto.setMessage("UPDATE fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in AlbumController.updateAlbum()", e);
        }
        return commonRespDto;
    }

    @DeleteMapping("/album/{id}")
    public CommonRespDto deleteAlbum(@PathVariable long id) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            albumService.deleteAlbum(id);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND.value());
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in AlbumController.deleteAlbum()", e);
        }
        return commonRespDto;
    }
}