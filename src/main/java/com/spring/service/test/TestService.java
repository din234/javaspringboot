package com.spring.service.test;

import io.netty.util.HashedWheelTimer;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import java.io.IOException;
import java.util.HashMap;

@Service
public class TestService {

    @Autowired
    private RestHighLevelClient highLevelClient;


    // Test ket noi elasticsearch
    public ResponseEntity<?> connect(){
        final Header[] headers = {
            new BasicHeader("Content-type", "application/x-www-form-urlencoded")
        };

        RestClient lowLevelClient = highLevelClient.getLowLevelClient();

        try {
            lowLevelClient.performRequest(new Request("GET",""));
            lowLevelClient.close();
        } catch (IOException e){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("error",e.toString());
            return new ResponseEntity(
                    map,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("success"
                ,HttpStatus.ACCEPTED);
    }
}
