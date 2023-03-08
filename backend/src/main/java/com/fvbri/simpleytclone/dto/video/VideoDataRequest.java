package com.fvbri.simpleytclone.dto.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDataRequest extends VideoDTO {
    private Long videoStatusId;
}
