package com.ml.springwithlombok.services;

import com.ml.springwithlombok.dao.EmployeeImage;
import com.ml.springwithlombok.dto.EmployeeImageDto;
import com.ml.springwithlombok.repositories.EmployeeImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ml.springwithlombok.services.EmployeeService.employeeConverter;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeImageService {

    private final EmployeeImageRepository employeeImageRepository;

    public List<EmployeeImageDto> findAllEmployeeImages() {
        log.info("EmployeeImageService.findAllEmployeeImages() - retrieving all employee images");
        return getAddressDtoList(employeeImageRepository.findAll());
    }

    public byte[] findEmployeeImageById(final Long id) {
        log.info("EmployeeImageService.findEmployeeImageById() - retrieving employee image. value: {}", id);
        val employeeImage = employeeImageRepository.findByEmployeeId(id);
        return (null != employeeImage) ? employeeImage.getImage() : null;
    }

    public List<byte[]> findAllEmployeeImagesPath() {
        log.info("EmployeeImageService.findAllEmployeeImagesPath() - retrieving all employee images path");
        val all = employeeImageRepository.findAll();
        val images = new ArrayList<byte[]>();
        for (EmployeeImage employeeImage : all) {
            images.add(employeeImage.getImage());
        }
        return images;
    }

    private List<EmployeeImageDto> getAddressDtoList(List<EmployeeImage> employeeImages) {
        val employeeImageDtoList = new ArrayList<EmployeeImageDto>();
        for (EmployeeImage employeeImage : employeeImages) {
            employeeImageDtoList.add(
                    new EmployeeImageDto(
                            employeeImage.getId(),
                            employeeConverter(employeeImage.getEmployee()),
                            employeeImage.getImage(),
                            employeeImage.getCreatedDate())
            );
        }
        return employeeImageDtoList;
    }
}
