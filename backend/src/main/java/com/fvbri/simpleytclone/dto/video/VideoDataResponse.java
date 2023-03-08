package com.fvbri.simpleytclone.dto.video;

import com.fvbri.simpleytclone.model.video.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDataResponse extends VideoDTO {
    private Long id;
    private String videoUrl;
    private String thumbnailUrl;

    private VideoStatus videoStatus;
    public VideoDataResponse(
            Long id,
            String thumbnailUrl,
            String title,
            String description,
            Set<String> tags,
            String videoUrl,
            VideoStatus videoStatus
            ) {
        super(title, description, tags);
        this.id = id;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.videoStatus = videoStatus;
    }



}
