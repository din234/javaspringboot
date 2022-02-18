package com.spring.service.test;

import com.spring.repositories.elastic.UserRepoElastic;
import com.spring.model.test.constant.Gender;
import com.spring.model.test.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Service // Convert to beans, same as @Component but for readabiliy
public class TestUserService {

    private UserRepoElastic userRepository;

    @Autowired
    public TestUserService(UserRepoElastic userRepository){
        this.userRepository = userRepository;
    }

    // TESTING
//    public List<User> getUsers(){
//        return List.of(
//                new User(
//                        "A",
//                        "A@example.com",
//                        Role.GUEST
//                )
//        );
//    }


    public List<Students> getStudents() {
        return List.of(
                new Students(
                        100L,
                        "Duy",
                        "Duy@example.com",
                        "StreetA",
                        "StreetB",
                        LocalDate.of(2000, Month.APRIL,5),
                        Gender.MALE
                )
        );
    }
}
