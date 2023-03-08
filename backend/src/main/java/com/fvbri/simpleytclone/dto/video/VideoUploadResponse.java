package com.fvbri.simpleytclone.dto.video;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoUploadResponse {
    private Long videoId;
    private String videoUrl;
}
