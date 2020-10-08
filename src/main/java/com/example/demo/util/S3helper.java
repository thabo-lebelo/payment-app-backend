package com.example.demo.util;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.example.demo.config.Props;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
@Component
public class S3helper {

    @Autowired
    private Props props;

    private AmazonS3 s3client;

    @PostConstruct
    private void initialize() {
        log.info("Initializing S3 client...");

        System.setProperty(SDKGlobalConfiguration.DISABLE_CERT_CHECKING_SYSTEM_PROPERTY, "true");

        AWSCredentials credentials = new BasicAWSCredentials(
                props.getAccessKeyId(), props.getSecretAccessKey()
        );

        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public void listBuckets() {
        List<Bucket> buckets = s3client.listBuckets();
        log.info("Buckets in your account:");
        for(Bucket bucket : buckets) {
            log.info(bucket.getName());
        }
    }

    public void uploadFile(String file) {
        s3client.putObject(props.getRemoteDirectory(), file, "Uploaded String Object");
    }

    @PreDestroy
    private void destroy() {
        log.info("Shutdown S3 client.");
        s3client.shutdown();
    }

}
