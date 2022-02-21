package com.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestIndex {
//    public static void main(String[] args){
//        SpringApplication.run(TestIndex.class);
//    }

    // Check new main context load thành công hay không
    @Test
    public void contextLoads() throws Exception {
        // Chạy test assertion
        // thông tin chi tiết tại
        // https://en.wikipedia.org/wiki/Test_assertion
//        assertThat(homeController).isNotNull();
    }
}