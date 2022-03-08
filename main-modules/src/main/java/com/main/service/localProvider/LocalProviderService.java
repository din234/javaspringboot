package com.spring.service.localProvider;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring.model.location.CountrySheet;
import com.spring.model.location.RegionSheet;
import com.spring.model.security.AuthoritySheet;
import com.spring.model.base.RecordTemplate;
import com.spring.util.ExcelUtil;
import com.spring.model.user.UserSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocalProviderService {
    private static final Logger logger = LoggerFactory.getLogger(LocalProviderService.class);

    @Value("${server.url}")
    private String URL;
    @Value("${server.port}")
    private String PORT;
    @Value("${excel.run}")
    private Boolean runExcel;
    @Value("${excel.dir}")
    private String dirPath;

    private final ObjectWriter ow;
    private final RestTemplate restTemplate;
    private final HttpHeaders header;

    private final ExcelUtil excelUtil;
    private final AuthoritySheet authoritySheet;
    private final UserSheet userSheet;
    private final RegionSheet regionSheet;
    private final CountrySheet countrySheet;

    @Autowired
    public LocalProviderService(
            ObjectWriter ow,
            RestTemplate restTemplate,
            HttpHeaders header,
            ExcelUtil excelUtil,

            AuthoritySheet authoritySheet,
            UserSheet usersheet,
            RegionSheet regionSheet,
            CountrySheet countrySheet
    ){
        this.ow = ow;
        this.restTemplate = restTemplate;
        this.header = header;

        this.excelUtil = excelUtil;
        this.authoritySheet = authoritySheet;
        this.userSheet = usersheet;
        this.regionSheet = regionSheet;
        this.countrySheet = countrySheet;
    }

    public void run() {
        set(authoritySheet, "authority", dirPath + "Authorities.xlsx");
        set(userSheet, "user", dirPath + "Users.xlsx");
        set(countrySheet, "location", dirPath + "Countries.xlsx");
    }

    private void set(RecordTemplate recordTemplate, String endPoint, String filePath) {
        try {
            final String url = URL + ":" + PORT + "/server/add/" + endPoint;
            final List<Object> objects = excelUtil.readExcel(recordTemplate, filePath);

            for (Object obj : objects) {
                post(obj, url);
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Async
    private void post(Object obj,String url) {
        try {
            long start = System.currentTimeMillis();
            String json = ow.writeValueAsString(obj);
            HttpEntity<String> request = new HttpEntity<>(json, header);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            long end = System.currentTimeMillis();

            logger.info("Start: " + start + " and End: " + end);
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}