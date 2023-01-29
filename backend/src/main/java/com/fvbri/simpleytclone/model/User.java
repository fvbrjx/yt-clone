package com.fvbri.simpleytclone.model;


import com.fvbri.simpleytclone.model.video.Video;
import com.fvbri.simpleytclone.model.video.VideoHistory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String emailAddress;

    @ManyToMany
    @JoinTable(name = "subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id"))
    private List<User> subscribers;

    @ManyToMany(mappedBy = "subscribers")
    private List<User> subscribed;

    @OneToMany(mappedBy = "user")
    private List<VideoHistory> videoHistory;

    @ManyToMany
    @JoinTable(name = "liked_videos",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id"))
    private List<Video> likedVideos;
}
