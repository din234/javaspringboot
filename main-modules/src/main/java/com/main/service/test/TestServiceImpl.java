package com.main.service.test;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;

@Service
public class TestServiceImpl implements TestService {
    Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);



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
                    map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("success"
                ,HttpStatus.ACCEPTED);
    }


    @Async
    public void asyncService(){
        try {
            Thread.sleep(2000);
            logger.info("Goi truoc");
        } catch (Exception e){}
    }



//    @Autowired
//    @Qualifier("springSecurityFilterChain")
//    private GenericFilterBean springSecurityFilterChain;

    @RequestMapping("/chainProxy")
    public ResponseEntity<?> getFilterChainProxy(){
//        FilterChainProxy filterChainProxy = (FilterChainProxy) springSecurityFilterChain;
//
//        List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
////        HashMap<Integer,String> map = new HashMap<Integer,String>();

        return new ResponseEntity("", HttpStatus.OK);
    }
}
