import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.upwork.UpworkApplication;
import com.spring.upwork.model.Job;
import com.spring.upwork.service.UpworkServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = UpworkApplication.class)
public class TestIndex {
    Logger logger = LoggerFactory.getLogger(TestIndex.class);

    @Autowired
    UpworkServiceImpl workService;

    @Test
    public void contextLoads() throws Exception {

        // Chạy test assertion
        // thông tin chi tiết tại
        // https://en.wikipedia.org/wiki/Test_assertion
//        assertThat(locationRepoElastic);
//        assertThat(locationServiceImpl).isNotNull();
    }


    @Test
    public void jsonReader(){
        ObjectMapper mapper = new ObjectMapper();
        try {
//            Map<?, ?> map = mapper.readValue(Paths.get("ftp://192.168.1.100/e/data1/0").toFile(), Map.class);

            List<Map<?,?>> col = mapper.readValue(Paths.get("C:\\Users\\Dim\\Desktop\\0.json").toFile(), List.class );
            List<Map<?,?>> jobs = (List<Map<?,?>>) ((Map<?, ?>) col.get(0).get("searchResults")).get("jobs");

            Job job = mapper.convertValue(jobs.get(2), Job.class);
            logger.info(job.toString());
            workService.addJob(job);

        } catch (Exception e){
            e.printStackTrace();
        }
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