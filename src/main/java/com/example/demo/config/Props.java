package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties("demo")
public class Props {

    @Value("${demo.access-key-id}")
    private String accessKeyId;

    @Value("${demo.secret-access-key}")
    private String secretAccessKey;

    @Value("${demo.remote-directory}")
    private String remoteDirectory;

    @Value("${demo.local-directory}")
    private String localDirectory;

    @Value("${demo.file-pass}")
    private String filePass;

    @Value("${demo.file-fail}")
    private String fileFail;

    @Value("${demo.file-name-pattern}")
    private String fileNamePattern;

}
