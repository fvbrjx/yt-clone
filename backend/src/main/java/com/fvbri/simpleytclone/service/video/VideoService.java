package com.fvbri.simpleytclone.service.video;

import com.fvbri.simpleytclone.dto.video.VideoDTOMapper;
import com.fvbri.simpleytclone.dto.video.VideoDataRequest;
import com.fvbri.simpleytclone.dto.video.VideoDataResponse;
import com.fvbri.simpleytclone.dto.video.VideoUploadResponse;
import com.fvbri.simpleytclone.model.video.Video;
import com.fvbri.simpleytclone.repository.video.VideoRepository;
import com.fvbri.simpleytclone.utils.AppUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final FileService fileService;
    private final VideoRepository videoRepository;
    private final VideoDTOMapper videoDTOMapper;

    public VideoUploadResponse uploadVideo(MultipartFile file) {
        // Upload file to s3
        final String videoUrl = fileService.uploadFile(file);
        Video video = Video.builder()
                .videoUrl(videoUrl)
                .build();

        //  save video to the db
        final Long videoId = videoRepository.saveAndFlush(video).getVideoId();
        return new VideoUploadResponse(videoId, videoUrl);
    }

    public void updateThumbnail(MultipartFile file, Long id) {
        final Video video = getVideoById(id);

        // Upload file to s3
        final String thumbnailUrl = fileService.uploadFile(file);

        video.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(video);
    }

    public void editVideo(VideoDataRequest videoDataRequest, Long videoId) {
        final Video video = getVideoById(videoId);

        AppUtils.updateEntityFields(
                Video.class,
                videoDTOMapper.requestToVideo(videoDataRequest),
                video
        );

        videoRepository.save(video);
    }

    private Video getVideoById(Long videoId) {
        final Video video = videoRepository.findById(videoId).orElseThrow(
                () ->
                        new EntityNotFoundException(
                                "Video with id %s not found".formatted(videoId)
                        )
        );
        return video;
    }

    public VideoDataResponse getVideoDetails(Long videoId) {
        final Video video = getVideoById(videoId);
        VideoDataResponse videoResponse = videoDTOMapper.videoToResponse(video);
        return videoResponse;
    }
}
