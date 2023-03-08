package com.fvbri.simpleytclone.model.video;

import com.fvbri.simpleytclone.model.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "video")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long videoId;

    private String title;
    private String description;
    private int likes;
    private int dislikes;
    private Set<String> tags;
    @Column(name = "video_url")
    private String videoUrl;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(
            name ="video_status_id",
            referencedColumnName = "id"
    )
    private VideoStatus videoStatus;
    @Column(name = "view_count")
    private int viewCount;
    @Column(name = "thumb_nail_url")
    private String thumbnailUrl;

    @OneToMany(mappedBy = "video", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
}
