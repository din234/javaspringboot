package com.spring.model.excel;
import com.spring.model.jpa.Authority;
import com.spring.model.user.User;
import com.spring.repositories.jpa.AuthorityRepoSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class UserSheet extends RecordTemplateImpl<User> {

    @Autowired
    private AuthorityRepoSql authorityRepoSql;


    private final List<BiConsumer<User,Object>> userRecord = List.of (
            (user,val) -> user.setUsername((String) val),
            (user,val) -> user.setPassword((String) val),
            (user,val) -> user.setFullName((String) val),
            (user,val) -> user.setEmail((String) val),
            (user,val) -> {
                String str = (String) val;
                if (str == null)return;
                String[] auth = str.split(";");
                for (String i : auth) {
//                    System.out.println(str);
                    Authority authority = authorityRepoSql.findByTitle(i);
                    user.getAuthorities().add(authority);
                }
            }
    );
    public UserSheet () {
        super(User::new);
    }

    @Override
     public List<BiConsumer<User, Object>> setMapping() {
        return this.userRecord;
    }
}