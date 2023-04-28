package com.ml.springwithlombok.services;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.InputStream;

@Slf4j
@Service
public class FileService {
    @SneakyThrows
    public byte[] retrieveStockImage(String imageName) {
        log.info("LombokDemoApplication.retrieveStockImage() - attempting to retrieve the image");
        ClassPathResource classPathResource = new ClassPathResource(imageName);
        @Cleanup InputStream in = classPathResource.getInputStream();
        byte[] imageBytes = StreamUtils.copyToByteArray(in);
        log.info("LombokDemoApplication.retrieveStockImage() - byte[] length = " + imageBytes.length);
        return imageBytes;
    }
}