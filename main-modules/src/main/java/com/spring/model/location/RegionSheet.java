package com.spring.model.location;

import com.spring.model.base.RecordTemplateImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class RegionSheet extends RecordTemplateImpl<Region>{
    public RegionSheet() {
        super(Region::new);
    }

    private final List<BiConsumer<Region,Object>> countryRecord = List.of (
            (col,val) -> {},
            (col,val) -> col.setRegion_title((String) val),
            (col,val) -> col.setGlobal_location((String) val)
    );

    @Override
    public List<BiConsumer<Region, Object>> setMapping() {
        return this.countryRecord;
    }
}
