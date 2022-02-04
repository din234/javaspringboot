package com.spring.service.user;

import com.spring.repositories.UserRepo;
import com.spring.util.Gender;
import com.spring.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Service // Convert to beans, same as @Component but for readabiliy
public class TestUserService {

    private UserRepo userRepository;

    @Autowired
    public TestUserService(UserRepo userRepository){
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
