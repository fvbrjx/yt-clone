package com.fvbri.simpleytclone.repository.video;

import com.fvbri.simpleytclone.model.video.VideoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoStatusRepository extends JpaRepository<VideoStatus, Long> {
}
