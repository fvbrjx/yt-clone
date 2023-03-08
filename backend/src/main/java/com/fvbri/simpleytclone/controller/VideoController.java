package com.fvbri.simpleytclone.controller;

import com.fvbri.simpleytclone.dto.video.VideoDataRequest;
import com.fvbri.simpleytclone.dto.video.VideoDataResponse;
import com.fvbri.simpleytclone.dto.video.VideoUploadResponse;
import com.fvbri.simpleytclone.model.response.Response;
import com.fvbri.simpleytclone.service.video.VideoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/video")
@RequiredArgsConstructor
@Tag(name = "Video")
@SecurityRequirement(name = "bearerAuth")
public class VideoController {
    private final VideoService videoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> uploadVideo(@RequestPart("file") MultipartFile file) {
        final VideoUploadResponse videoDataResponse = videoService.uploadVideo(file);

        return ResponseEntity.created(null).body(
                Response.<VideoUploadResponse>builder()
                        .success(true)
                        .data(videoDataResponse)
                        .message("The video was uploaded correctly")
                        .build()
        );
    }

    @PutMapping("thumbnail/{videoId}")
    public ResponseEntity<Response> updateThumbnail(
            @RequestParam("file") MultipartFile file,
            @PathVariable("videoId") Long id
    ) {
        videoService.uploadVideo(file);

        return ResponseEntity.created(null).body(
                Response.builder()
                        .success(true)
                        .message("The thumbnail was updated correctly")
                        .build()
        );
    }


    @PutMapping("{videoId}")
    public ResponseEntity<Response> editVideoMetaData(
            @RequestBody VideoDataRequest videoDataRequest,
            @PathVariable("videoId") Long id
    ) {
        videoService.editVideo(videoDataRequest, id);
        return ResponseEntity
                .ok()
                .body(
                        Response.builder()
                                .success(true)
                                .message("The video was updated correctly")
                                .build()
                );
    }

    @GetMapping("details/{videoId}")
    public ResponseEntity<Response> getVideoDetails(@PathVariable("videoId") Long videoId) {
        final VideoDataResponse videoDetails = videoService.getVideoDetails(videoId);
        return ResponseEntity
                .ok()
                .body(
                        Response.builder()
                                .success(true)
                                .data(videoDetails)
                                .message("Video details")
                                .build()
                );
    }


}
