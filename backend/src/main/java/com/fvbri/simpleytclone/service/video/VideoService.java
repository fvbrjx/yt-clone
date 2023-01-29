package com.fvbri.simpleytclone.service.video;

import com.fvbri.simpleytclone.model.video.Video;
import com.fvbri.simpleytclone.repository.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final FileService fileService;
    private final VideoRepository videoRepository;
    public void uploadVideo(MultipartFile file) {
        // Upload file to s3
        final String videoUrl = fileService.uploadFile(file);
        Video video = Video.builder()
                .videoUrl(videoUrl)
                .build();

        //  save video to the db
        videoRepository.save(video);
    }
}
