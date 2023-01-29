package com.fvbri.simpleytclone.repository.video;

import com.fvbri.simpleytclone.model.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
