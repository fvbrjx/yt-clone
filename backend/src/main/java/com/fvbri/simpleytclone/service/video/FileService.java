package com.fvbri.simpleytclone.service.video;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String uploadFile(MultipartFile file);

}
