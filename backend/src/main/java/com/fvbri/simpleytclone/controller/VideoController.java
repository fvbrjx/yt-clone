package com.fvbri.simpleytclone.controller;

import com.fvbri.simpleytclone.model.response.Response;
import com.fvbri.simpleytclone.service.video.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    @PostMapping
    public ResponseEntity<Response> uploadVideo(@RequestParam("file") MultipartFile file) {
        videoService.uploadVideo(file);

        return ResponseEntity.created(null).body(
                Response.builder()
                        .success(true)
                        .message("The video was uploaded correctly")
                        .build()
        );
    }
}
