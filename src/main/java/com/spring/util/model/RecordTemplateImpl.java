package com.spring.util.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;


/**
 * Lấy record template trong excel:
 * VD template: | username | password | email | ...
 * Tạo model = cách kế thừa class này
 * @param <T>
 */
public abstract class RecordTemplateImpl<T> implements RecordTemplate {
    private static Logger logger = LoggerFactory.getLogger(RecordTemplateImpl.class);

    private final Supplier<? extends T> impl;

    interface runExcel { Object run(Cell cell);}
    private final Map<CellType, runExcel> cellTypeRunMap;
    private List<BiConsumer<T,Object>> list;

    public RecordTemplateImpl(Supplier<? extends T> impl) {
        this.impl = Objects.requireNonNull(impl);

        // Tạo function Hashmap
        cellTypeRunMap = new HashMap<>();
        cellTypeRunMap.put(CellType._NONE, cell -> null);
        cellTypeRunMap.put(CellType.NUMERIC, Cell::getNumericCellValue);
        cellTypeRunMap.put(CellType.STRING, Cell::getStringCellValue);
        cellTypeRunMap.put(CellType.FORMULA, Cell::getCellFormula);
        cellTypeRunMap.put(CellType.BLANK, cell -> null);
        cellTypeRunMap.put(CellType.BOOLEAN, cell -> null);
        cellTypeRunMap.put(CellType.ERROR, cell -> null);
    }

    // Cấu hình mapping cho khớp giữa model và excel column
    @Override
    public abstract List<BiConsumer<T,Object>> setMapping();

    // Đọc dữ liệu file excel từng dòng một
    @Override
    public T getRecord(Row row){
        T field = impl.get();
        List<BiConsumer<T,Object>> list = setMapping();
        for (int i = 0; i < list.size(); ++i){
            Cell cell = row.getCell(i);
            Object val = null;
            try {
                val = cellTypeRunMap.get(cell.getCellType()).run(cell);
            } catch (Exception e){
                logger.error(e.getMessage());
            }
            list.get(i).accept(field,val);
        }
        return field;
    }
}
