package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api")
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private TestService testService;


    @PostMapping("/search")
    public ResponseEntity getSearchResult() throws InterruptedException{
        testService.sleepFor();
//        logger.info("Fast as fuck boy");
        return new ResponseEntity("Acknowledge", HttpStatus.OK);
    }

    @PostMapping("/async")
    public ResponseEntity asyncResponse(){
        return new ResponseEntity("TEST",HttpStatus.OK);
    }


//    https://spring.io/blog/2012/05/07/spring-mvc-3-2-preview-introducing-servlet-3-async-support
    @PostMapping("callable")
    public Callable<String> processUpload(){
        // Spring MVC will then invoke the Callable in a separate thread with the help of a TaskExecutor
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "test";
            }
        };
    }


    @PostMapping("ok")
    public void callBack(){
        DeferredResult<ResponseEntity> output = new DeferredResult<>();


        // Acknowledge
        // Sau tao url callback
    }

//    @PostMapping("deferred")
//    @ResponseBody
//    public DeferredResult<String> deferredResult(){
//
//    }
}
