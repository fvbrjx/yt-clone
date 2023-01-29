package com.fvbri.simpleytclone.config.AWS;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    private final String ACCESS_KEY;
    private final String SECRET_KEY;

    public S3Config(@Value("${spring.aws-access-key}") String ACCESS_KEY,
                    @Value("${spring.aws-secret-key}") String SECRET_KEY
    ) {
        this.ACCESS_KEY = ACCESS_KEY;
        this.SECRET_KEY = SECRET_KEY;
    }

    @Bean
    public  AmazonS3Client amazonS3Client() {
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSCredentialsProvider() {
                    @Override
                    public AWSCredentials getCredentials() {
                        AWSCredentials awsCredentials = new AWSCredentials() {
                            @Override
                            public String getAWSAccessKeyId() {
                                return ACCESS_KEY;
                            }

                            @Override
                            public String getAWSSecretKey() {
                                return SECRET_KEY;
                            }
                        };
                        return awsCredentials;
                    }

                    @Override
                    public void refresh() {

                    }
                })
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }
}
