package com.example.sehnerbackend.utils;

import com.example.sehnerbackend.entities.Company;
import com.example.sehnerbackend.models.SearchParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static com.example.sehnerbackend.utils.StringLiterals.*;

@Slf4j
public class Helpers {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static Double getRevenue(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate getDate(String dateString) {
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCsvStringFromFile(String filename, ResourceLoader resourceLoader) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data/" + filename);
        InputStream inputStream = resource.getInputStream();
        byte[] fileData = FileCopyUtils.copyToByteArray(inputStream);
        return new String(fileData, StandardCharsets.UTF_8);
    }

    public static String extractDomain(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            if (domain != null) {
                return domain.startsWith("www.") ? domain.substring(4) : domain;
            }
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static File getResultsFile() {

        File file = new File(RESULTS_FILE);

        if (!file.getParentFile().exists()) {
            boolean created = file.getParentFile().mkdirs();
            if (!created) {
                log.error(DIRECTORIES_ERROR);
                throw new RuntimeException(DIRECTORIES_ERROR);
            }
        }
        return file;
    }

    public static List<String[]> getHeader() {
        return Arrays.asList(new String[][] {{"id", "name", "founded_on", "revenue", "website", "category"}});
    }

    public static String[] getArray(Company company) {

        String foundedOn = "";
        try{
            foundedOn = company.getFoundedOn().format(formatter);
        } catch (Exception ignored) {}
        String revenue = company.getRevenue() != null ? company.getRevenue().toString() : "";

        return new String[] {
                company.getId(),
                company.getName(),
                foundedOn,
                revenue,
                company.getWebsite(),
                company.getCategory()
        };
    }

    public static void sanitizeParams(SearchParams params) {
        if(params.getSearchBy() == null) {
            params.setSearchBy("");
        }

        if(params.getMaxRevenue() == null && params.getMinRevenue() == null) {
            return;
        }

        if(params.getMaxRevenue() == null || params.getMaxRevenue().intValue() == 0) {
            params.setMaxRevenue(new BigDecimal("9999999999999999999999"));
        }

        if(params.getMinRevenue() == null) {
            params.setMinRevenue(new BigDecimal("0"));
        }
    }
}
