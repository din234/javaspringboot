package com.spring.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.config.CustomException.IncorrectFileExtensionException;
import com.spring.model.user.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    @Autowired
    ObjectMapper objectMapper;

    @Value("${excel.dir}")
    private String filePath;

    interface runExcel { public Object run(Cell cell);}
    private Map<CellType, runExcel> cellTypeRunMap;
    private List<BiConsumer<User,Object>> userSetter = List.of (
            (user,val) -> user.setUsername((String) val),
            (user,val) -> user.setPassword((String) val),
            (user,val) -> user.setFullName((String) val),
            (user,val) -> user.setEmail((String) val)
    );

    public ExcelUtil() {
        cellTypeRunMap = new HashMap<>();
        cellTypeRunMap.put(CellType._NONE, cell -> null);
        cellTypeRunMap.put(CellType.NUMERIC, cell -> cell.getNumericCellValue());
        cellTypeRunMap.put(CellType.STRING, cell -> cell.getStringCellValue());
        cellTypeRunMap.put(CellType.FORMULA, cell -> cell.getCellFormula());
        cellTypeRunMap.put(CellType.BLANK, cell -> null);
        cellTypeRunMap.put(CellType.BOOLEAN, cell -> null);
        cellTypeRunMap.put(CellType.ERROR, cell -> null);
    }

    //    https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
    private Map<String,Object> convertObjectToMap(User user){
        return objectMapper.convertValue(user,Map.class);
    }

    public List<User> readExcel() throws IOException {
        List<User> users = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(this.filePath));
        Workbook workbook = getWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            User user = getRecord(row);
            users.add(user);
        }
        workbook.close();
        inputStream.close();
        return users;
    }

    private User getRecord(Row row) {
        User user = new User();
        for (int i = 0; i < userSetter.size(); ++i){
            Cell cell = row.getCell(i);
            Object val = cellTypeRunMap.get(cell.getCellType()).run(cell);
            userSetter.get(i).accept(user,val);
        }
        return user;
    }

    private Workbook getWorkbook(FileInputStream inputStream) throws IOException{
        Workbook workbook = null;
        String fileExtension = FilenameUtils.getExtension(this.filePath);
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
