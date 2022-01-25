package com.spring.service.test;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private RestHighLevelClient highLevelClient;

    // Test ket noi elasticsearch
    public List<?> checkDB(){
        final Header[] headers = {
            new BasicHeader("Content-type", "application/x-www-form-urlencoded")
        };
        RestClient lowLevelClient = highLevelClient.getLowLevelClient();

        try {
            lowLevelClient.performRequest(new Request("GET",""));
            lowLevelClient.close();
        } catch (IOException e){
            return List.of(e.toString());
        }

        return List.of(1,2,3,4,5);
    }
}
