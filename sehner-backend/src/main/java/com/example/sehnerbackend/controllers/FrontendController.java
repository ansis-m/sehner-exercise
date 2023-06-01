package com.example.sehnerbackend.controllers;

import com.example.sehnerbackend.entities.Company;
import com.example.sehnerbackend.models.SearchParams;
import com.example.sehnerbackend.repositories.CompaniesRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static com.example.sehnerbackend.utils.Helpers.sanitizeParams;
import static com.example.sehnerbackend.utils.StringLiterals.RESULTS_FILE;

@RestController
@Data
@CrossOrigin
public class FrontendController {

    private final CompaniesRepository companiesRepository;

    @PostMapping("/companies/search")
    public Page<Company> getCompanies(@RequestBody SearchParams params){

        sanitizeParams(params);
        if (params.isAscending()) {
            if(params.getMaxRevenue() == null && params.getMinRevenue() == null) {
                return companiesRepository.findByDynamicCriteria(params, null);
            }
            return companiesRepository.findByDynamicCriteriaWithRange(params, null);
        } else if (params.getMaxRevenue() == null && params.getMinRevenue() == null) {
            return companiesRepository.findByDynamicCriteriaDescending(params, null);
        }
        return companiesRepository.findByDynamicCriteriaWithRangeDescending(params, null);
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile() throws IOException {
        Path filePath = Paths.get(RESULTS_FILE);
        byte[] fileData = Files.readAllBytes(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"companies-with-categories.csv\"");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }
}
