package com.fvbri.simpleytclone.service.video;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.fvbri.simpleytclone.exception.video.VideoUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService {
    private final String BUCKET_NAME = "yt-clone-videosfile";
    private final AmazonS3Client amazonS3Client;

    @Override
    public String uploadFile(MultipartFile file) {
        // Preparare a key
        var filenameExtensions = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID().toString() + "." +  filenameExtensions;

        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            final PutObjectResult putObjectResult = amazonS3Client.putObject(
                    BUCKET_NAME,
                    key,
                    file.getInputStream(),
                    metadata);
        } catch (IOException e) {
            throw new VideoUploadException("An exception occured while uploading a file");
        }
        amazonS3Client.setObjectAcl(
                BUCKET_NAME,
                key,
                CannedAccessControlList.PublicRead);

        final String resourceUrl = amazonS3Client.getResourceUrl(BUCKET_NAME, key);
//        amazonS3Client.deleteObject(BUCKET_NAME, key);
        return resourceUrl;
    }


}
