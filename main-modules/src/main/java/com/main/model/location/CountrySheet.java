package com.main.model.location;
import com.main.model.base.RecordTemplateImpl;
import com.main.repositories.jpa.RegionRepoSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class CountrySheet extends RecordTemplateImpl<Country> {

    @Autowired
    private RegionRepoSQL regionRepoSQL;

    public CountrySheet() {
        super(Country::new);
    }

    private final List<BiConsumer<Country,Object>> countryRecord = List.of (
            (col,val) -> col.setCountry_title((String) val),
            (col,val) -> {
                col.getRegion().setRegion_title((String) val);
            },
            (col,val) -> col.getRegion().setGlobal_location((String) val)
    );

    @Override
     public List<BiConsumer<Country, Object>> setMapping() {
        return this.countryRecord;
    }
}