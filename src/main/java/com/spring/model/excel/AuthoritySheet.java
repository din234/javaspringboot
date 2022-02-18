package com.spring.model.excel;

import com.spring.model.jpa.Authority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class AuthoritySheet extends RecordTemplateImpl<Authority>{
    private final List<BiConsumer<Authority,Object>> authorityRecord = List.of (
            (authority,val) -> authority.setTitle((String) val),
            (authority,val) -> authority.setNumber(((Double) val).intValue())
    );

    public AuthoritySheet () {
        super(Authority::new);
    }

    @Override
    public List<BiConsumer<Authority, Object>> setMapping() {
        return this.authorityRecord;
    }
}