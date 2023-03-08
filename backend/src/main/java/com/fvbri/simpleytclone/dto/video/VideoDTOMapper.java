package com.fvbri.simpleytclone.dto.video;


import com.fvbri.simpleytclone.model.video.Video;
import com.fvbri.simpleytclone.model.video.VideoStatus;
import com.fvbri.simpleytclone.repository.video.VideoStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoDTOMapper {
    private final VideoStatusRepository videoStatusRepository;
    public Video requestToVideo(VideoDataRequest videoDataRequest) {
        final VideoStatus videoStatus = videoStatusRepository.findById(videoDataRequest.getVideoStatusId()      ).get();

        return Video.builder()
                .title(videoDataRequest.getTitle())
                .description(videoDataRequest.getDescription())
                .tags(videoDataRequest.getTags())
                .videoStatus(videoStatus)
                .build();
    }


    public VideoDataResponse videoToResponse(Video video) {
        return new VideoDataResponse(
                video.getVideoId(),
                video.getThumbnailUrl(),
                video.getTitle(),
                video.getDescription(),
                video.getTags(),
                video.getVideoUrl(),
                video.getVideoStatus()
        );
    }
}
