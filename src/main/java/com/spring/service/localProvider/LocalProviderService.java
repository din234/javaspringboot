package com.spring.service.localProvider;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring.model.excel.AuthoritySheet;
import com.spring.model.excel.RecordTemplate;
import com.spring.model.jpa.Authority;
import com.spring.repositories.jpa.AuthorityRepoSql;
import com.spring.util.ExcelUtil;
import com.spring.model.excel.UserSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocalProviderService {
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
    private final AuthorityRepoSql authorityRepoSql;
    private final AuthoritySheet authoritySheet;
    private final UserSheet userSheet;

    @Autowired
    public LocalProviderService(
            ObjectWriter ow,
            RestTemplate restTemplate,
            HttpHeaders header,

            ExcelUtil excelUtil,
            AuthorityRepoSql authorityRepoSql,
            AuthoritySheet authoritySheet,
            UserSheet usersheet
    ){
        this.ow = ow;
        this.restTemplate = restTemplate;
        this.header = header;

        this.excelUtil = excelUtil;
        this.authorityRepoSql = authorityRepoSql;
        this.authoritySheet = authoritySheet;
        this.userSheet = usersheet;
    }

    public ResponseEntity run() throws Exception {
        set(authoritySheet, "authority",dirPath+"Authorities.xlsx");
        set(userSheet, "user",dirPath+"Users.xlsx");

        return new ResponseEntity("Data added!", HttpStatus.OK);
    }

    private void set(RecordTemplate recordTemplate, String endPoint, String filePath) throws Exception {
        final String addUserUrl = URL+":"+PORT+"/server/add/"+endPoint;
        final List<Object> users = excelUtil.readExcel(recordTemplate,filePath);

        for (Object user : users) {
            String json = ow.writeValueAsString(user);
            try {
                HttpEntity<String> request = new HttpEntity<>(json, header);
                ResponseEntity<String> response = restTemplate.postForEntity(addUserUrl, request, String.class);

                System.out.println(response);
            } catch (Exception e) {
//                    e.printStackTrace();
            }
        }
    }

    public ResponseEntity addAuthority(Authority authority){
        return new ResponseEntity(authorityRepoSql.save(authority),HttpStatus.OK);
    }
}