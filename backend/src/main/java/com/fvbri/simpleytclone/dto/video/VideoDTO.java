package com.fvbri.simpleytclone.dto.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {
    private String title;
    private String description;
    private Set<String> tags;

}
