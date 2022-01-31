package com.spring.service.user;

import com.spring.model.jwt.User;
import com.spring.respositories.UserRepository;
import com.spring.util.Gender;
import com.spring.model.Students;
import com.spring.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Service // Convert to beans, same as @Component but for readabiliy
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // TESTING
    public List<User> getUsers(){
        return List.of(
                new User(
                        "A",
                        "A@example.com",
                        Role.GUEST
                )
        );
    }


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
