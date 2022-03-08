package com.main.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import com.main.config.CustomException.IncorrectFileExtensionException;
import com.main.model.base.RecordTemplate;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtil {
    private Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    //    https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
//    private Map<String,Object> convertObjectToMap(Object user){
//        return objectMapper.convertValue(user,Map.class);
//    }

    public List<Object> readExcel(RecordTemplate recordTemplate, String filePath) throws IOException {
        List<Object> entities = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = getWorkbook(inputStream,filePath);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next(); // Ignore header
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            Object entity = recordTemplate.getRecord(row);
            entities.add(entity);
        }
        workbook.close();
        inputStream.close();
        return entities;
    }

    private Workbook getWorkbook(FileInputStream inputStream, String filePath) throws IOException{
        Workbook workbook = null;
        String fileExtension = FilenameUtils.getExtension(filePath);
        switch (fileExtension){
            case ("xlsx"):
                workbook = new XSSFWorkbook(inputStream);
                break;
            case ("xls"):
                workbook = new HSSFWorkbook(inputStream);
                break;
//            case ("csv"):
//                break;
            default:
                logger.error("The file extensions is: "+fileExtension);
                throw new IncorrectFileExtensionException("Invalid Extension for excel: "+fileExtension);
        }
        return workbook;
    }
}
