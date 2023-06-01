package com.example.sehnerbackend.services;

import com.example.sehnerbackend.entities.Company;
import com.example.sehnerbackend.repositories.CompaniesRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.sehnerbackend.utils.Helpers.*;
import static com.example.sehnerbackend.utils.StringLiterals.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final HashMap<String, String> websitesMap = new HashMap<>();
    private final ArrayList<String[]> resultsArray = new ArrayList<>(getHeader());
    private final ResourceLoader resourceLoader;
    private final CompaniesRepository companiesRepository;
    public void processFiles() throws IOException {

        boolean resultsFileCreated = filesProcessed();
        boolean dbInitialized = companiesRepository.countEntries() > 1;

        if (resultsFileCreated && dbInitialized) {
            log.info(FILES_PROCESSED);
            return;
        }

        getWebsitesHashMap();
        String companiesCsv = getCsvStringFromFile(COMPANIES, resourceLoader);
        CSVParser csvParser = CSVParser.parse(companiesCsv, CSVFormat.DEFAULT);
        csvParser.stream().skip(1).forEach(record -> {
            Company company = buildCompany(record);
            if (!dbInitialized) {
                companiesRepository.saveAndFlush(company);
            }
            resultsArray.add(getArray(company));
        });
        if(!resultsFileCreated) {
            writeResultsFile();
        }
        csvParser.close();
    }

    private void writeResultsFile() {
        File resulsFile = getResultsFile();
        resultsArray.forEach(result -> {
            try (CSVWriter writer = new CSVWriter(new FileWriter(resulsFile, true))) {
                    writer.writeNext(result);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }

    private boolean filesProcessed() {
        File resultsFile = new File(RESULTS_FILE);
        return resultsFile.exists();
    }

    private void getWebsitesHashMap() throws IOException {

        String csvContent = getCsvStringFromFile(WEBSITES, resourceLoader);
        CSVParser csvParser = CSVParser.parse(csvContent, CSVFormat.DEFAULT);
        csvParser.stream().skip(1).forEach(csvRecord -> {
            if (csvRecord.values().length == 2 && !websitesMap.containsKey(csvRecord.get(0))) {
                websitesMap.put(csvRecord.get(0), csvRecord.get(1));
            } else if (websitesMap.containsKey(csvRecord.get(0))){
                log.error(REPEATING_KEYS
                        + String.format(REPORT_VALUES, csvRecord.get(0), csvRecord.get(1), websitesMap.get(csvRecord.get(0))));
            } else {
                log.error(WEBSITES + WRONG_NUMBER_OF_VALUES + String.join(", ", csvRecord.toList()));
            }
        });
        csvParser.close();
    }

    public Company buildCompany(CSVRecord record) {
        return Company.builder()
                .id(record.get(0))
                .name(record.get(1))
                .foundedOn(getDate(record.get(2)))
                .revenue(getRevenue(record.get(3)))
                .website(record.get(4))
                .category(getCategory(record.get(4)))
                .build();
    }

    private String getCategory(String url) {
        String domain = extractDomain(url);
        return websitesMap.getOrDefault(domain, null);
    }
}
