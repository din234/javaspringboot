package com.spring.service.localProvider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring.util.ExcelUtil;
import com.spring.util.model.UserSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocalProviderService {


    @Autowired
    private ExcelUtil readExcelFile;

    @Value("${server.url}")
    private String URL;
    @Value("${server.port}")
    private String PORT;
    @Value("${excel.run}")
    private Boolean runExcel;


    public ResponseEntity run() throws Exception {
        final String addUserUrl = URL+":"+PORT+"/server/add";

        final List<Object> users = readExcelFile.readExcel(new UserSheet());

        ObjectWriter ow = new ObjectMapper().writer();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

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
        return new ResponseEntity("Data added!", HttpStatus.OK);
    }
}
