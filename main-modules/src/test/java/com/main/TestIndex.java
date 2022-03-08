package com.main;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class TestIndex {
    Logger logger = LoggerFactory.getLogger(TestIndex.class);


//    @BeforeTestClass
//    public static void insertDriver(){
//        driver = new ChromeDriver();
//    }

//    @Mock
//    LocationRepoElastic locationRepoElastic;
//
//    @InjectMocks
//    LocationServiceImpl locationServiceImpl;

    // Check new main context load thành công hay không
    @Test
    public void contextLoads() throws Exception {

        // Chạy test assertion
        // thông tin chi tiết tại
        // https://en.wikipedia.org/wiki/Test_assertion
//        assertThat(locationRepoElastic);
//        assertThat(locationServiceImpl).isNotNull();
    }



//    @Test
//    public void getAll(){
//        List<LocationSearch> mockLocation = new ArrayList<>();
//        when(locationServiceImpl.getLocations()).thenReturn(mockLocation);
//        assertThat(mockLocation).isNotNull();
//        logger.info(mockLocation.toString());
////        assertThis();
//    }
}