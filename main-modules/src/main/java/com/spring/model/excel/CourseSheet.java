package com.spring.model.excel;//package com.spring.model.excel;
//
//import com.spring.model.jpa.Course;
//
//import java.util.List;
//import java.util.function.BiConsumer;
//
//public class CourseSheet extends RecordTemplateImpl<Course>{
//    private final List<BiConsumer<Course,Object>> authorityRecord = List.of (
////            (authority,val) -> authority.setTitle((String) val),
////            (authority,val) -> authority.setNumber(((Double) val).intValue())
//    );
//
//    public CourseSheet() {
//        super(Course::new);
//    }
//
//    @Override
//    public List<BiConsumer<Course, Object>> setMapping() {
//        return null;
//    }
//}