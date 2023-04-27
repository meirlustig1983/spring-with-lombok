package com.ml.springwithlombok.controllers;

import com.ml.springwithlombok.dto.EmployeeImageDto;
import com.ml.springwithlombok.services.EmployeeImageService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = {"/api/v1/employees/images/"})
public class EmployeeImageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeImageController.class);
    private final EmployeeImageService employeeImageService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeImageDto>> listEmployees() {
        val employeeImageList = employeeImageService.findAllEmployeeImages();
        if (employeeImageList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeeImageList);
    }

    @GetMapping(value = "/{employeeId}/employeeId", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable Long employeeId) {
        byte[] imageBytes = employeeImageService.findEmployeeImageById(employeeId);

        LOGGER.info("size " + imageBytes.length);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}